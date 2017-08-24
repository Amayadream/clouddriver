package com.amayadream.clouddriver.web.controller;

import com.amayadream.clouddriver.core.enums.FilesStatusEnum;
import com.amayadream.clouddriver.common.result.ResultConstant;
import com.amayadream.clouddriver.common.result.ResultObject;
import com.amayadream.clouddriver.common.result.Results;
import com.amayadream.clouddriver.common.util.Constants;
import com.amayadream.clouddriver.common.util.FileMd5Utils;
import com.amayadream.clouddriver.core.dao.FilesRepository;
import com.amayadream.clouddriver.core.model.Files;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

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
     * 验证文件是否存在
     * @param fileMd5   文件md5
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Results upload(MultipartFile file, String fileName, Long size, Integer total, Integer index, String fileMd5, String shardingMd5, String action) throws IOException {
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

        //3.如果操作为上传前检查
        if ("c".equalsIgnoreCase(action)) {
            //如果分片文件存在
            if (sharding.exists()) {
                //检查分片文件md5
                String md5 = FileMd5Utils.md5(sharding);
                //如果相等则直接返回成功
                if (md5.equalsIgnoreCase(shardingMd5)) {
                    return Results.ok(ResultConstant.SUCCESS);
                }
                //如果不相等则是分片文件不完整, 先删除再返回未上传状态, 准备上传分片
                sharding.deleteOnExit();
                return Results.nok(ResultConstant.FILE_NOT_EXIST, "该分片尚未上传!");
            }
            //如果分片不存在, 则直接返回未上传状态
            return Results.nok(ResultConstant.FILE_NOT_EXIST, "该分片尚未上传!");
        } else {
            sharding.deleteOnExit();
            //TODO 上传分片
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
