package com.amayadream.clouddriver.common.cache;

import com.amayadream.clouddriver.common.cache.config.RedisExpireConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Redis封装类, 包含一些常用的方法
 * @author : Amayadream
 * @date :   2017-08-21 09:43
 */
@Component
public class RedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisService.class);

    private static final String DEFAULT_LOCK_VALUE = "locked";
    /* 默认尝试时间 */
    private static final String DEFAULT_TIME_OUT = "500";
    /* 默认尝试时间单位 */
    private static final TimeUnit DEFAULT_TIME_OUT_UNIT = TimeUnit.MILLISECONDS;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedisExpireConfig redisExpireConfig;

    /**
     * 非阻塞锁
     * @param key           锁键
     * @param value         被谁锁定
     * @param expire        失效时间
     * @param expireUnit    失效时间单位
     */
    public boolean lock(String key, String value, long expire, TimeUnit expireUnit) {
        return lock(key, value, 0L, null, expire, expireUnit);
    }

    /**
     * 阻塞锁
     * @param key           锁键
     * @param value         被谁锁定
     * @param timeout       尝试获取锁时长
     * @param timeoutUnit   尝试时间单位
     * @param expire        锁被动失效时间
     * @param expireUnit    锁被动失效时间单位
     */
    public boolean lock(String key, String value, long timeout, TimeUnit timeoutUnit, long expire, TimeUnit expireUnit) {
        try {
            long start = System.nanoTime();
            do {
                if (redisTemplate.getConnectionFactory().getConnection().setNX(key.getBytes(),
                        value.getBytes())) {
                    redisTemplate.expire(key, expire, expireUnit);
                    return Boolean.TRUE;
                }
                Thread.sleep(100);
            } while (timeout != 0L && (System.nanoTime() - start) < timeoutUnit.toNanos(timeout));
            return Boolean.FALSE;
        } catch (Exception e) {
            logger.error("[RedisService] 加锁失败, 错误原因: {}", e.getMessage());
            return Boolean.TRUE;
        }
    }

    /**
     * 释放锁
     * @param key   锁键
     */
    public void unlock(String key) {
        redisTemplate.delete(key);
    }

    public void hset(String redisKey, String key, String value) {
        redisTemplate.opsForHash().put(redisKey, key, value);
    }

    public String hget(String redisKey, String key) {
        return (String) redisTemplate.opsForHash().get(redisKey, key);
    }

    public void set(String redisKey, String value) {
        redisTemplate.opsForValue().set(redisKey, value);
    }

    public String get(String redisKey) {
        return (String) redisTemplate.opsForValue().get(redisKey);
    }

    public Long incr(String redisKey) {
        return redisTemplate.opsForValue().increment(redisKey, 1L);
    }

    public Long decr(String redisKey, long n) {
        return redisTemplate.opsForValue().increment(redisKey, -1 * n);
    }

    public void del(String redisKey) {
        redisTemplate.delete(redisKey);
    }

    public void hdel(String redisKey, String key) {
        redisTemplate.opsForHash().delete(redisKey, key);
    }

    public void setAndExpire(String redisKey, Object value, long expire, TimeUnit expireUnit) {
        redisTemplate.opsForValue().set(redisKey, value, expire, expireUnit);
    }

    public void hsetAndExpire(String hashKey, String key, String value, long expireSeconds) {
        Instant timestamp = Instant.now().plusSeconds(expireSeconds);

        redisTemplate.opsForHash().put(hashKey, key, value);
        redisTemplate.opsForHash().put(redisExpireConfig.getKey(), buildExpireKey(hashKey, key), timestamp.toEpochMilli());
    }

    public String hgetAndExpire(String hashKey, String key) {
        String value = (String) redisTemplate.opsForHash().get(hashKey, key);
        String expireId = buildExpireKey(hashKey, key);    //生成expire中存储的key
        if (StringUtils.isEmpty(value)) {
            redisTemplate.opsForHash().delete(redisExpireConfig.getKey(), expireId);
            return null;
        }
        Long timestamp = (Long) redisTemplate.opsForHash().get(redisExpireConfig.getKey(), expireId);
        if (StringUtils.isEmpty(timestamp)) {
            return null;
        }
        if (Instant.now().toEpochMilli() > timestamp) {
            redisTemplate.opsForHash().delete(hashKey, key);
            redisTemplate.opsForHash().delete(redisExpireConfig.getKey(), expireId);
            return null;
        }
        return value;
    }

    /**
     * 组装有效期hash中的key
     *
     * @param hashKey 保存数据的hash redisKey
     * @param key     保存数据的hash key
     */
    private String buildExpireKey(String hashKey, String key) {
        return hashKey + redisExpireConfig.getSeparator() + key;
    }

}
