package com.amayadream.clouddriver.exception;

/**
 * 文件夹不存在异常
 * @author :  Amayadream
 * @date :  2016.09.26 16:55
 */
public class FolderNotFoundException extends Exception {

    public FolderNotFoundException() {
    }

    public FolderNotFoundException(String message) {
        super(message);
    }

}
