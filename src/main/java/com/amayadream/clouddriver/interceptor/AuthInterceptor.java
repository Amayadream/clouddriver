package com.amayadream.clouddriver.interceptor;

import com.amayadream.clouddriver.exception.AuthorizationException;
import com.amayadream.clouddriver.utils.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 认证拦截器, 负责对未登陆用户的操作进行拦截
 * @author :  Amayadream
 * @date :  2016.10.18 09:29
 */
public class AuthInterceptor implements HandlerInterceptor {

    private List<String> excludedUrls;  //排除的URI
    private List<String> excludedRegex; //正则匹配URI

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取URI后缀
        String requestUri = request.getServletPath();

        //首段匹配排除拦截地址
        for(String uri : excludedRegex){
            if (requestUri.startsWith(uri)) {
                return true;
            }
        }

        //过滤不需要拦截的地址
        for (String uri : excludedUrls) {
            if (requestUri.endsWith(uri)) {
                return true;
            }
        }

        //登录拦截器
        HttpSession session = request.getSession();
        if (session.getAttribute(Constants.SESSION_USERID) == null) {
            throw new AuthorizationException(Constants.EXCEPTION_MSG_AUTH);
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    public List<String> getExcludedRegex() {
        return excludedRegex;
    }

    public void setExcludedRegex(List<String> excludedRegex) {
        this.excludedRegex = excludedRegex;
    }
}
