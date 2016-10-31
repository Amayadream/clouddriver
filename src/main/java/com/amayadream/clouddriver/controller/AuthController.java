package com.amayadream.clouddriver.controller;

import com.amayadream.clouddriver.model.User;
import com.amayadream.clouddriver.service.IUserService;
import com.amayadream.clouddriver.utils.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 认证控制器
 * @author :  Amayadream
 * @date :  2016.10.17 21:19
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @Resource
    private IUserService userService;

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
    public String login(User user, HttpSession session, RedirectAttributes attributes){
        if(!StringUtils.isEmpty(user.getUserId())  && !StringUtils.isEmpty(user.getPassword())){
            User u = userService.findById(user.getUserId());
            if(u != null){
                if (u.getPassword().equals(DigestUtils.md5Hex(user.getPassword() + u.getSalt()))) {
                    session.setAttribute(Constants.SESSION_USERID, u.getUserId());
                    session.setAttribute(Constants.SESSION_USER, u);
                    attributes.addFlashAttribute("message", "登陆成功!");
                    return "redirect:/driver/home";
                }else{
                    //密码错误
                    attributes.addFlashAttribute("error", "用户密码错误!");
                }
            }else{
                //用户不存在
                attributes.addFlashAttribute("error", "用户不存在!");
            }
        }else{
            //用户名或密码不能为空!
            attributes.addFlashAttribute("error", "用户名和密码不能为空!");
        }
        return "redirect:/auth/login";
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
