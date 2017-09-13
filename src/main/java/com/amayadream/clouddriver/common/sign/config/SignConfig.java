package com.amayadream.clouddriver.common.sign.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 签名参数配置类
 * @author : xjding
 * @date :   2017-09-13 16:57
 */
@Configuration
@ConfigurationProperties("clouddriver.sign")
public class SignConfig {

    /* 密钥 */
    private String secret;
    /* 请求过期时间 */
    private Integer expireTime;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }
}
