package com.amayadream.clouddriver.service;

import com.amayadream.clouddriver.model.FileLibrary;

import java.util.Date;

/**
 * @author :  Amayadream
 * @date :  2016.09.30 16:26
 */
public interface IFileLibraryService {

    /**
     * 判断文件是否存在于文件库
     */
    boolean isExist(String fileMd5);

    void insert(FileLibrary fileLibrary);

}
