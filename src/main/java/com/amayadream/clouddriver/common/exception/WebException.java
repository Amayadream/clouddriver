package com.amayadream.clouddriver.common.exception;

import com.amayadream.clouddriver.common.result.ResultConstant;

/**
 * Web层异常
 * @author :  Amayadream
 * @date :  2017.08.21 23:32
 */
public class WebException extends RuntimeException {

    private ResultConstant constant;

    public WebException(ResultConstant constant) {
        super(constant.message);
        this.constant = constant;
    }

    public ResultConstant getConstant() {
        return constant;
    }

    public void setConstant(ResultConstant constant) {
        this.constant = constant;
    }

}
