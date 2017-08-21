package com.amayadream.clouddriver.common.util;

import com.google.common.base.Charsets;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Amayadream
 * @date :   2017-08-16 18:37
 */
public class SignHelper {

    public String getRequestContent(HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {

        } else {

        }
        return null;
    }

    public static String createSign(String params, String secret) {
        return DigestUtils.md5DigestAsHex((secret + "|" + params).getBytes(Charsets.UTF_8));
    }

}
