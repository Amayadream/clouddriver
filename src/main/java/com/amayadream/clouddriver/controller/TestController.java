package com.amayadream.clouddriver.controller;

import com.mongodb.gridfs.GridFSFile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author :  Amayadream
 * @date :  2016.09.28 15:16
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private GridFsTemplate gridFsTemplate;

    @RequestMapping(value = "/add", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add() throws FileNotFoundException {
        File file = new File("d:/package.json");
        InputStream is = new FileInputStream(file);
        GridFSFile fsFile = gridFsTemplate.store(is, "package.json", "json");
        return "文件ID: " + fsFile.getId() + "文件名:" + fsFile.getFilename() + ", 文件大小: " + fsFile.getLength() + "字节, 文件MD5: " + fsFile.getMD5();
    }


}
