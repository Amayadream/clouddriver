package com.amayadream.clouddriver.controller;

import com.amayadream.clouddriver.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 认证控制器
 * @author :  Amayadream
 * @date :  2016.10.17 21:19
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    /**
     * 跳转至登陆页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "auth/login";
    }

    /**
     * 登陆
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session){

        return null;
    }

    /**
     * 跳转至注册页面
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "auth/register";
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String regsiter(User user){

        return null;
    }

    /**
     * 注销
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/auth/login";
    }

}
