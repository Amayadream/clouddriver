package com.amayadream.clouddriver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 文件实体
 * @author :  Amayadream
 * @date :  2016.09.26 13:21
 */
@Document(collection = "file")
@Repository
public class FileCommon {

    /** 文件ID */
    @Id
    private String fileId;
    /** 用户名 */
    private String userId;
    /** 文件夹ID */
    private String folderId;
    /** 文件名称 */
    private String fileName;
    /** 文件拓展名 */
    private String fileExt;
    /** 文件大小 */
    private long fileSize;
    /** 文件MD5(文件库的ID) */
    private String fileMd5;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date modifyTime;
    /** 文件状态, 1:正常, 0:回收站 */
    private int status;


    public FileCommon() {

    }

    public FileCommon(String fileId, String userId, String folderId, String fileName, String fileExt, long fileSize, String fileMd5, Date createTime, Date modifyTime, int status) {
        this.fileId = fileId;
        this.userId = userId;
        this.folderId = folderId;
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.fileSize = fileSize;
        this.fileMd5 = fileMd5;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.status = status;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
