package com.amayadream.clouddriver.core.enums;

/**
 * @author :  Amayadream
 * @date :  2017.08.24 22:04
 */
public enum FilesStatusEnum {

    CREATING(0, "正在上传"),
    COMPLETED(1, "正常"),

    ;

    public int value;
    public String description;

    FilesStatusEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

}
