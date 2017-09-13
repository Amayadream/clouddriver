package com.amayadream.clouddriver.web.controller;

import com.amayadream.clouddriver.common.result.ResultConstant;
import com.amayadream.clouddriver.common.result.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : xjding
 * @date :   2017-09-13 15:39
 */
@Controller
@RequestMapping(value = "/sample")
public class SampleController {

    private static Logger logger = LoggerFactory.getLogger(SampleController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public Results hello(String a, String b) {

        logger.info("a: {}", a);
        logger.info("b: {}", b);

        return Results.ok(ResultConstant.SUCCESS);
    }


}
