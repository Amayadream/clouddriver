package com.amayadream.clouddriver.controller;

import com.amayadream.clouddriver.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 用户控制器
 * @author :  Amayadream
 * @date :  2016.10.19 10:02
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = {Constants.SESSION_USERID, Constants.SESSION_USER})
public class UserController {

    @RequestMapping(value = "/profile")
    public void profile(){

    }



}
