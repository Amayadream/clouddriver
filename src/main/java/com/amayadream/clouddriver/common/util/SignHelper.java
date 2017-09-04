package com.amayadream.clouddriver.common.util;

import com.google.common.base.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

/**
 * @author : Amayadream
 * @date :   2017-08-16 18:37
 */
public class SignHelper {

    /**
     * 从Request中获取签名校验所需的content
     */
    public String getRequestContent(HttpServletRequest request) {
        String content = null;
        if (RequestMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
            content = buildSortedParams(request.getParameterMap());
        } else {
            InputStream is = null;
            try {
                is = request.getInputStream();
                content = IOUtils.toString(is, Charsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
        return content;
    }

    /**
     * 生成签名
     * @param sortedParams  排序后的参数字符串
     * @param secret        私钥
     */
    public static String createSign(String sortedParams, String secret) {
        return DigestUtils.md5DigestAsHex((secret + "|" + sortedParams).getBytes(Charsets.UTF_8));
    }

    /**
     * 通过参数map生成排序后的url查询字符串
     * @param map   参数map
     */
    public static String buildSortedParams(Map<String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return  "";
        }
        StringBuilder sb = new StringBuilder();
        Object[] keys = map.keySet().toArray();
        Arrays.sort(keys);
        for (Object key : keys) {
            String[] v = map.get(key);
            if (v == null || v.length == 0) {
                buildQueryString(sb, key, null);
                continue;
            }
            for (String s : v) {
                buildQueryString(sb, key, s);
            }
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

    /**
     * 通过key和value组装查询参数
     * @param sb    StringBuilder
     * @param key   key
     * @param value value
     */
    public static void buildQueryString(StringBuilder sb, Object key, Object value) {
        try {
            sb.append(key).append("=").append(value == null ? "" : URLEncoder.encode(value.toString(), "utf-8")).append("&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
