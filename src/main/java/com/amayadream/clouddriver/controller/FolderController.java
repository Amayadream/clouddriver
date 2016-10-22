package com.amayadream.clouddriver.controller;

import com.amayadream.clouddriver.exception.FolderNotFoundException;
import com.amayadream.clouddriver.service.IFolderService;
import com.amayadream.clouddriver.utils.Constants;
import com.amayadream.clouddriver.utils.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;

/**
 * 文件夹控制器
 * @author :  Amayadream
 * @date :  2016.09.26 15:50
 */
@Controller
@RequestMapping(value = "/folder")
@SessionAttributes(value = Constants.SESSION_USERID)
public class FolderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IFolderService folderService;

    /**
     * 新建文件夹
     */
    @RequestMapping(value = "/create")
    public Results add(@RequestParam(defaultValue = "0") String folderPid, @RequestParam String folderName, @ModelAttribute(Constants.SESSION_USERID) String userId) {
        try {
            return folderService.add(userId, folderPid, folderName) == 1 ? Results.success("添加成功!") : Results.error("添加失败, 文件夹名称重复!");
        } catch (FolderNotFoundException e) {
            logger.debug("新建文件夹失败, 错误原因: {}", e.getMessage());
            return Results.error(e.getMessage());
        }
    }

    /**
     * 重命名
     */
    @RequestMapping(value = "/edit")
    public Results edit(@RequestParam String folderId, @RequestParam String folderName, @ModelAttribute(Constants.SESSION_USERID) String userId){
        try {
            folderService.update(userId, folderId, folderName);
            return Results.success("修改成功!");
        } catch (FolderNotFoundException e) {
            logger.debug("重命名文件夹失败, 错误原因: {}", e.getMessage());
            return Results.error(e.getMessage());
        }
    }

}
