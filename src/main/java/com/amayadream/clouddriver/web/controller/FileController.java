package com.amayadream.clouddriver.web.controller;

import com.amayadream.clouddriver.common.result.ResultConstant;
import com.amayadream.clouddriver.common.result.ResultObject;
import com.amayadream.clouddriver.common.result.Results;
import com.amayadream.clouddriver.common.util.Constants;
import com.amayadream.clouddriver.common.util.FileMd5Utils;
import com.amayadream.clouddriver.core.dao.FilesRepository;
import com.amayadream.clouddriver.core.enums.FilesStatusEnum;
import com.amayadream.clouddriver.core.model.Files;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author :  Amayadream
 * @date :  2017.08.22 20:48
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    @Resource
    private FilesRepository filesRepository;

    /**
     * 跳转首页
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "file";
    }

    /**
     * 验证文件是否存在
     * @param fileMd5   文件md5
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    @ResponseBody
    public Results validate(String fileMd5) {
        if (StringUtils.isEmpty(fileMd5)) {
            return Results.nok(ResultConstant.EMPTY_PARAM);
        }
        Files files = filesRepository.findByMd5(fileMd5);
        if (files == null || files.getStatus() != FilesStatusEnum.COMPLETED.value) {
            return Results.nok(ResultConstant.FILE_NOT_EXIST);
        }

        ResultObject result = new ResultObject.Builder()
                .append("file", files)
                .build();

        return Results.ok(ResultConstant.SUCCESS, result);
    }

    /**
     * 分片上传文件
     * @param file          分片文件
     * @param fileName      总文件名称
     * @param size          总文件大小
     * @param total         分片总个数
     * @param index         当前分片索引
     * @param fileMd5       总文件md5
     * @param shardingMd5   分片文件md5
     * @param action        操作类型
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Results upload(MultipartFile file, String fileName, Long size, Integer total, Integer index,
                          String fileMd5, String shardingMd5, String action) throws IOException {
        if (!"c".equalsIgnoreCase(action) && !"u".equalsIgnoreCase(action)) {
            return Results.nok(ResultConstant.EMPTY_PARAM, "操作类型不正确!");
        }

        //1.验证总文件是否已经上传过
        Files files = filesRepository.findByMd5(fileMd5);
        if (files != null && files.getStatus() == FilesStatusEnum.COMPLETED.value) {
            return Results.nok(ResultConstant.FILE_EXIST);
        }

        //2.找到总文件保存目录
        String savePath = buildSavePath(fileMd5);
        String saveDirectory = buildFullPath(savePath);
        File path = new File(saveDirectory);
        if (!path.exists()) {
            path.mkdirs();
        }

        File sharding = new File(saveDirectory, String.valueOf(index));

        //3.检查/上传分片
        if ("c".equalsIgnoreCase(action)) { //如果操作为上传前检查
            if (!sharding.exists()) {   //如果分片文件不存在, 则直接返回未上传状态
                return Results.nok(ResultConstant.FILE_NOT_EXIST, "该分片尚未上传!");
            }
            String md5 = FileMd5Utils.md5(sharding);
            if (!md5.equalsIgnoreCase(shardingMd5)) {   //如果不相等则是分片文件不完整, 先删除再返回未上传状态
                sharding.deleteOnExit();
                return Results.nok(ResultConstant.FILE_NOT_EXIST, "该分片尚未上传!");
            }
            //如果相等则直接返回成功
            return Results.ok(ResultConstant.SUCCESS);
        } else {    //如果是上传则直接上传文件
            sharding.deleteOnExit();
            file.transferTo(sharding);
        }

        if (path.isDirectory()) {
            File[] fileArray = path.listFiles();
            if (fileArray != null) {
                if (fileArray.length == 1) {    //第一次上传, 则记录总文件
                    Files totalFile = new Files();
                    totalFile.setName(fileName);
                    totalFile.setSuffix(FilenameUtils.getName(fileName));
                    totalFile.setMd5(fileMd5);
                    totalFile.setSize(0L);
                    totalFile.setStatus(FilesStatusEnum.CREATING.value);
                    totalFile.setCreatedTime(new Date());
                    filesRepository.save(totalFile);
                } else if (fileArray.length == total) { //所以分片都上传完毕, 准备合并
                    File totalFile = new File(buildFullPath(buildSavePath(fileMd5)), fileName);
                    FileOutputStream fos = new FileOutputStream(totalFile, true);
                    byte[] bytes = new byte[10 * 1024 * 1024];
                    int len;
                    FileInputStream tmp = null; //分片文件
                    for (int i = 0; i < total; i++) {
                        tmp = new FileInputStream(new File(saveDirectory, String.valueOf(i + 1)));
                        while ((len = tmp.read(bytes)) != -1) {
                            fos.write(bytes, 0, len);
                        }
                    }
                    if (tmp != null) {
                        tmp.close();
                    }
                    fos.close();

                    //保存文件
                    Files f = filesRepository.findByMd5(fileMd5);
                    f.setStatus(FilesStatusEnum.COMPLETED.value);
                    filesRepository.save(f);
                }
            }
        }

        return Results.ok(ResultConstant.SUCCESS);
    }

    private static String buildFullPath(String savePath) {
        return "f:/" + savePath;
    }

    private static String buildSavePath(String fileMd5) {
        return Constants.BASE_FILE_PATH + File.separator + fileMd5;
    }

}
