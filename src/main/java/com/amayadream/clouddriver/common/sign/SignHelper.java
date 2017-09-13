package com.amayadream.clouddriver.common.sign;

import com.google.common.base.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
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
     * 从request中获取签名所需的排序后的content
     */
    public static String getSortedContent(HttpServletRequest request) {
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
     * @param accessToken   访问令牌
     * @param timestamp     时间戳
     */
    public static String createSign(String sortedParams, String secret, String accessToken, String timestamp) {
        if (StringUtils.isEmpty(accessToken)) {
            return DigestUtils.md5DigestAsHex((sortedParams + secret + accessToken + timestamp).getBytes(Charsets.UTF_8));
        } else {
            return DigestUtils.md5DigestAsHex((sortedParams + secret + timestamp).getBytes(Charsets.UTF_8));
        }
    }

    /**
     * 通过参数map生成排序后的url查询字符串
     * @param map   参数map
     */
    private static String buildSortedParams(Map<String, String[]> map) {
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
    private static void buildQueryString(StringBuilder sb, Object key, Object value) {
        try {
            sb.append(key).append("=").append(value == null ? "" : URLEncoder.encode(value.toString(), "utf-8")).append("&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
