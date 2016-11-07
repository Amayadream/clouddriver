package com.amayadream.clouddriver.controller;

import com.amayadream.clouddriver.service.IShareService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 共享控制器
 * @author :  Amayadream
 * @date :  2016.11.07 16:48
 */
@Controller
@RequestMapping(value = "/share")
public class ShareController {

    @Resource
    private IShareService shareService;

    /**
     * 个人共享页面
     * @return
     */
    @RequestMapping(value = "")
    public String share(){
        return null;
    }

    @RequestMapping(value = "/{shareId}")
    public String getShare(@PathVariable String shareId) {
        return null;
    }

    @RequestMapping(value = "/validate/{shareId}/{sharePwd}")
    public String validate(@PathVariable String shareId, @PathVariable String sharePwd) {
        return null;
    }




}
