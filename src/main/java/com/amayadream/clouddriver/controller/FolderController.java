package com.amayadream.clouddriver.controller;

import com.amayadream.clouddriver.exception.FolderNotFoundException;
import com.amayadream.clouddriver.service.IFolderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author :  Amayadream
 * @date :  2016.09.26 15:50
 */
@Controller
@RequestMapping(value = "folder")
public class FolderController {

    @Resource
    private IFolderService folderService;

    @RequestMapping(value = "add")
    @ResponseBody
    public String add(@RequestParam(defaultValue = "0") String folderPid, @RequestParam String folderName) throws FolderNotFoundException {
        String userId = "Amayadream";
        folderService.add(userId, folderPid, folderName);
        return "SUCCESS";
    }


}
