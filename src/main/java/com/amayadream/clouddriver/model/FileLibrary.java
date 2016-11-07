package com.amayadream.clouddriver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

/**
 * @author :  Amayadream
 * @date :  2016.09.28 11:30
 */
@Document(collection = "fileLibrary")
@Repository
public class FileLibrary implements Serializable {

    /** 文件ID(文件md5) */
    @Id
    private String fileId;
    /** 文件名称 */
    private String fileName;
    /** 文件拓展名 */
    private String fileExt;
    /** 文件大小 */
    private long fileSize;
    /** 关联文件(文件实体) */
    private String fileLink;
    /** 文件创建时间 */
    private Date createTime;
    /** 文件修改时间(最后一次被使用) */
    private Date modifyTime;
    /** 文件状态(1:正常, -1:封禁) */
    private int status;

    /** 常量: 文件状态: 正常 */
    public static final int STATUS_NORMAL = 1;
    /** 常量: 文件状态: 回收站 */
    public static final int STATUS_FORB = -1;

    public FileLibrary() {

    }

    public FileLibrary(String fileId, String fileName, String fileExt, long fileSize, String fileLink, Date createTime, Date modifyTime, int status) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.fileSize = fileSize;
        this.fileLink = fileLink;
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

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
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
