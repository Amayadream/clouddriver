package com.amayadream.clouddriver.web.controller;

import com.amayadream.clouddriver.common.result.ResultConstant;
import com.amayadream.clouddriver.common.result.Results;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author :  Amayadream
 * @date :  2017.08.22 20:48
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Results validate(String fileMd5) {



        return Results.ok(ResultConstant.SUCCESS);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Results upload(MultipartFile file, String fileName, Long size, Integer total, Integer index, String fileMd5, String shardingMd5) {



        return Results.ok(ResultConstant.SUCCESS);
    }

}
