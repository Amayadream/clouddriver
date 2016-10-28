package com.amayadream.clouddriver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 * @author :  Amayadream
 * @date :  2016.10.28 20:04
 */
@RequestMapping(value = "/")
@Controller
public class IndexController {

    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }

}
