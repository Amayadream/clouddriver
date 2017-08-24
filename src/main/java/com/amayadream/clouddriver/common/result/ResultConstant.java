package com.amayadream.clouddriver.common.result;

/**
 * @author : Amayadream
 * @date :   2017-08-17 14:40
 */
public enum  ResultConstant {

    SUCCESS(0, "ok"),

    FILE_NOT_EXIST(2001, "文件不存在"),
    FILE_EXIST(2002, "文件已存在"),


    EMPTY_PARAM(4001, "参数缺失"),

    ;

    public long code;
    public String message;

    ResultConstant(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
