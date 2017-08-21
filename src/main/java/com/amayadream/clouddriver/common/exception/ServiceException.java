package com.amayadream.clouddriver.common.exception;

import com.amayadream.clouddriver.common.result.ResultConstant;

/**
 * 业务错误
 * @author : Amayadream
 * @date :   2017-08-17 14:37
 */
public class ServiceException extends RuntimeException {

    private ResultConstant constant;

    public ServiceException(ResultConstant constant) {
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
