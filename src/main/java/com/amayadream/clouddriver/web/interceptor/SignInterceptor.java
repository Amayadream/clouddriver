package com.amayadream.clouddriver.web.interceptor;

import com.amayadream.clouddriver.common.sign.config.SignConfig;
import com.amayadream.clouddriver.common.sign.SignHelper;
import com.amayadream.clouddriver.common.sign.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

/**
 * @author : Amayadream
 * @date :   2017-08-16 18:27
 */
@Component
public class SignInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SignInterceptor.class);

    @Resource
    private SignConfig signConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean flag = validateSign(request);
        if (!flag) {
            response.setStatus(403);
            return false;
        }
        logger.info("签名校验成功!");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }

    private boolean validateSign(HttpServletRequest request) {
        String timestampStr = request.getHeader(Headers.HEADER_TIMESTAMP);
        String sign = request.getHeader(Headers.HEADER_SIGN);
        String accessToken = request.getHeader(Headers.HEADER_ACCESS_TOKEN);
        if (StringUtils.isEmpty(timestampStr) || StringUtils.isEmpty(sign)) {
            logger.error("签名和时间戳参数丢失!");
            return false;
        }
        Long timestamp;
        try {
            timestamp = Long.parseLong(timestampStr);
        } catch (NumberFormatException e) {
            logger.error("时间戳格式错误!");
            return false;
        }
        if (Instant.now().toEpochMilli() - timestamp > signConfig.getExpireTime() * 60 * 1000) {
            logger.error("请求过期!");
            return false;
        }
        String content = SignHelper.getSortedContent(request);
        String signStr = SignHelper.createSign(content, signConfig.getSecret(), accessToken, timestampStr);
        if (!sign.equalsIgnoreCase(signStr)) {
            logger.error("签名校验失败!");
            return false;
        }

        return true;
    }

}
