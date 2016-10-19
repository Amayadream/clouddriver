package com.amayadream.clouddriver.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 日志拦截器, 负责拦截请求记录日志, 优先级最低
 * @author :  Amayadream
 * @date :  2016.08.26 15:37
 */
@Order(3)
@Aspect
@Component
public class LogInterceptor {

    private static final Logger logger = LogManager.getLogger(LogInterceptor.class);


    /** 认证操作 */
    @Pointcut("execution(public * com.amayadream.clouddriver.controller.FileController.*(..))")
    public void auth(){

    }

    /**
     * 用户登陆日志
     */
    @AfterReturning(value = "auth()")
    public void auth(JoinPoint point){

    }


}
