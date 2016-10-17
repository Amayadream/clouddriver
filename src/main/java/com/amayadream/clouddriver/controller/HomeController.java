package com.amayadream.clouddriver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author :  Amayadream
 * @date :  2016.10.17 11:17
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @RequestMapping(value = "/home")
    public String home(){
        return "index";
    }

}
