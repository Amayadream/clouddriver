package com.amayadream.clouddriver.service;

import com.amayadream.clouddriver.exception.FileCommonNotFoundException;
import com.amayadream.clouddriver.model.FileCommon;

import java.util.Date;
import java.util.List;

/**
 * @author :  Amayadream
 * @date :  2016.09.26 13:31
 */
public interface IFileService {

    /**
     * 根据文件ID查询自己的文件实体
     */
    FileCommon findById(String userId, String fileId);

    /**
     * 根据文件夹ID和关键词(可为空)查询自己的文件列表
     */
    List<FileCommon> find(String userId, String folderId, String search);

    /**
     * 添加文件
     */
    void insert(FileCommon fileCommon);

    /**
     * 更新文件
     */
    void rename(String userId, String fileId, String fileName) throws FileCommonNotFoundException;

    /**
     * 根据文件ID删除文件实体
     */
    int remove(String userId, String[] fileIds);

}
