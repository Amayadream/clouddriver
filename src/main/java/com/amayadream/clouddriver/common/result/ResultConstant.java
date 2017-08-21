package com.amayadream.clouddriver.common.result;

/**
 * @author : Amayadream
 * @date :   2017-08-17 14:40
 */
public enum  ResultConstant {

    SUCCESS(0, "ok")

    ;

    public long code;
    public String message;

    ResultConstant(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
