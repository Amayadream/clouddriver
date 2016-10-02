package com.amayadream.clouddriver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author :  Amayadream
 * @date :  2016.09.26 15:38
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

}
