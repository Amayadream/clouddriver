package com.amayadream.clouddriver.common.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Amayadream
 * @date :   2017-08-31 14:57
 */
@Configuration
@ConfigurationProperties(prefix = "clouddriver.redis.expire")
public class RedisExpireConfig {

    private String key;
    private String separator;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
