package com.amayadream.clouddriver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

/**文件夹实体
 * @author :  Amayadream
 * @date :  2016.09.26 13:21
 */
@Document(collection = "folder")
@Repository
public class FolderCommon implements Serializable {

    /** 文件夹ID */
    @Id
    private String folderId;
    /** 文件夹父ID, 根节点为0 */
    private String folderPid;
    /** 文件夹名称 */
    private String folderName;
    /** 用户名 */
    private String userId;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date modifyTime;
    /** 文件夹状态 1:正常, 0:回收站 */
    private int status;

    public FolderCommon() {

    }

    public FolderCommon(String folderId, String folderPid, String folderName, String userId, Date createTime, Date modifyTime, int status) {
        this.folderId = folderId;
        this.folderPid = folderPid;
        this.folderName = folderName;
        this.userId = userId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.status = status;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getFolderPid() {
        return folderPid;
    }

    public void setFolderPid(String folderPid) {
        this.folderPid = folderPid;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
