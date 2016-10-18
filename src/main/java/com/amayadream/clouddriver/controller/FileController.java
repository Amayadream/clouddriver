package com.amayadream.clouddriver.controller;

import com.amayadream.clouddriver.model.FileCommon;
import com.amayadream.clouddriver.model.FileLibrary;
import com.amayadream.clouddriver.service.IFileLibraryService;
import com.amayadream.clouddriver.service.IFileService;
import com.amayadream.clouddriver.utils.Constants;
import com.amayadream.clouddriver.utils.MD5Util;
import com.amayadream.clouddriver.utils.Results;
import com.amayadream.clouddriver.utils.StringUtil;
import com.mongodb.gridfs.GridFSFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author :  Amayadream
 * @date :  2016.09.28 16:23
 */
@Controller
@RequestMapping(value = "/file")
@SessionAttributes(Constants.SESSION_USERID)
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

    /**
     * 文件上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestParam MultipartFile file, @RequestParam String folderId, @ModelAttribute String userId) throws IOException {
        String md5 = MD5Util.MD5(file.getInputStream());
        Date date = new Date();
        boolean flag = libraryService.isExist(md5);
        if(!flag){
            logger.debug("新文件{}第一次上传, 计算出MD值为{}", file.getOriginalFilename(), md5);
            GridFSFile store = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
            FileLibrary fileLibrary = new FileLibrary(store.getMD5(), file.getOriginalFilename(), FilenameUtils.getExtension(file.getOriginalFilename()), store.getLength(), String.valueOf(store.getId()), date, date, 1);
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

    /**
     * 文件下载
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws IOException {
        File file = new File("E:\\常用工具\\镜像\\CentOS-6.5-x86_64-bin-DVD1.iso");
        String fileNameEncode = new String("CentOS-6.5-x86_64-bin-DVD1.iso".getBytes(),"ISO8859-1");
        response.setContentType("application/x-msdownload");
        FileInputStream fis = new FileInputStream(file);
        response.setHeader("Content-Disposition","attachment;filename=\""+fileNameEncode+"\"");     //文件名经过处理,防止有空格时出现文件名不全的情况
        OutputStream os = response.getOutputStream();
        IOUtils.copy(fis,os);
    }


}
