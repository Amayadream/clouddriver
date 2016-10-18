package com.amayadream.clouddriver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author :  Amayadream
 * @date :  2016.10.18 09:41
 */
@Controller
@RequestMapping(value = "/driver")
public class DriverController {

    /**
     * 个人主页
     */
    @RequestMapping(value = "/home")
    public String home(){
        return "index";
    }

    /**
     * 下载
     */
    @RequestMapping(value = "/download")
    public void download(){

    }

    /**
     * 移入垃圾箱
     */
    @RequestMapping(value = "/trash")
    public void trash(){

    }

    /**
     * 删除
     */
    @RequestMapping(value = "delete")
    public void delete(){

    }


}
