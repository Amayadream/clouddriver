package com.amayadream.clouddriver.controller;

import com.amayadream.clouddriver.model.FileCommon;
import com.amayadream.clouddriver.model.FileLibrary;
import com.amayadream.clouddriver.service.IFileLibraryService;
import com.amayadream.clouddriver.service.IFileService;
import com.amayadream.clouddriver.utils.MD5Util;
import com.amayadream.clouddriver.utils.Results;
import com.amayadream.clouddriver.utils.StringUtil;
import com.mongodb.gridfs.GridFSFile;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * @author :  Amayadream
 * @date :  2016.09.28 16:23
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    /** 日志 */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IFileService fileService;
    @Resource
    private IFileLibraryService libraryService;
    @Resource
    private GridFsTemplate gridFsTemplate;

    /**
     * 判断文件是否存在于文件库中, 1:存在, -1:不存在
     */
    @RequestMapping(value = "/isExist", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Results validate(@RequestParam String fileMd5){
        boolean flag = libraryService.isExist(fileMd5);
        logger.debug("文件检验结果: {}", flag ? "已存在":"尚未存在");
        return flag ? Results.success("文件已存在于文件库中, 可以秒传!") : Results.error("文件第一次上传, 开始上传!");
    }

    @RequestMapping(value = "/upload")
    public void upload(@RequestParam MultipartFile file, @RequestParam String folderId) throws IOException {
        String md5 = MD5Util.MD5(file.getInputStream());
        Date date = new Date();
        boolean flag = libraryService.isExist(md5);
        if(!flag){
            logger.debug("新文件{}第一次上传, 计算出MD值为{}", file.getOriginalFilename(), md5);
            GridFSFile store = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
            FileLibrary fileLibrary = new FileLibrary(store.getMD5(), String.valueOf(store.getId()), store.getLength(), date, date, 1);
            libraryService.insert(fileLibrary);
            FileCommon fileCommon = new FileCommon(StringUtil.generateGuid(), "Amayadream", folderId, store.getFilename(), FilenameUtils.getExtension(store.getFilename()), store.getLength(), store.getMD5(), date, date, 1);
            fileService.insert(fileCommon);
            logger.debug("新文件{}上传完毕", file.getOriginalFilename());
        }else{
            logger.debug("文件{}, MD值{}, 已在文件库中发现, 正在进行秒传...", file.getOriginalFilename(), md5);
            FileCommon fileCommon = new FileCommon(StringUtil.generateGuid(), "Amayadream", folderId, file.getOriginalFilename(), FilenameUtils.getExtension(file.getOriginalFilename()), file.getSize(), md5, date, date, 1);
            fileService.insert(fileCommon);
            logger.debug("文件{}秒传完毕.", file.getOriginalFilename());
        }
    }

}
