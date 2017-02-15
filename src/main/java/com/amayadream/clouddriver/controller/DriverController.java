package com.amayadream.clouddriver.controller;

import com.amayadream.clouddriver.utils.Constants;
import com.amayadream.clouddriver.utils.Results;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

/**
 *
 * @author :  Amayadream
 * @date :  2016.10.18 09:41
 */
@Controller
@RequestMapping(value = "/driver")
@SessionAttributes(value = {Constants.SESSION_USERID, Constants.SESSION_USER})
public class DriverController {

    /**
     * 个人主页
     */
    @RequestMapping(value = "/home")
    public String home(HttpSession session){
        return "index";
    }

    /**
     * 下载
     */
    @RequestMapping(value = "/download")
    public void download(@RequestParam String[] targets){

    }

    /**
     * 移入垃圾箱
     */
    @RequestMapping(value = "/trash")
    public Results trash(@RequestParam String[] targets){
        //参数格式为{"guid:type", ...}
        if(targets.length <= 0) return Results.error("没有选中任何文件或目录!");
        for (String target : targets) {
            String[] ts = target.split(":");
            if (ts.length != 2 || (!Constants.DRIVER_FILE.equals(ts[1]) && !Constants.DRIVER_FOLDER.equals(ts[1]))) return Results.error("参数格式有误, 请重新操作!");
        }
        //移入垃圾箱
        return null;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "delete")
    public void delete(@RequestParam String[] targets){

    }

    /**
     * 收藏
     */
    @RequestMapping(value = "/favourite")
    public void favourite(){

    }

    /**
     * 属性详情
     */
    @RequestMapping(value = "/info")
    public void info(@RequestParam String targets){

    }

    /**
     * 复制
     */
    @RequestMapping(value = "/copy")
    public void copy(@RequestParam String[] targets){

    }

    /**
     * 移动
     */
    @RequestMapping(value = "/move")
    public void move(@RequestParam String[] targets, @RequestParam String folderId) {

    }

    /**
     * 粘贴
     */
    @RequestMapping(value = "/parse")
    public void parse(){

    }


}
