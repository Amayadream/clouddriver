package com.amayadream.clouddriver.controller;

import com.amayadream.clouddriver.utils.Constants;
import com.amayadream.clouddriver.utils.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author :  Amayadream
 * @date :  2016.12.07 22:26
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {

    /**
     * 生成验证码
     * @throws IOException
     */
    @RequestMapping(value = "/createVerifyCode")
    public void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");   //设置response缓存和返回类型
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        String verifyCode = VerifyCode.generateVerifyCode(4);  //生成随机字串

        HttpSession session = request.getSession(true); //存入会话session
        session.setAttribute(Constants.SESSION_VERIFY_CODE, verifyCode.toLowerCase());

        VerifyCode.outputImage(90, 34, response.getOutputStream(), verifyCode);  //生成图片
    }
}
