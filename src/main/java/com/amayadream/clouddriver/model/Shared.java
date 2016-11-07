package com.amayadream.clouddriver.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 共享实体
 * @author :  Amayadream
 * @date :  2016.11.07 14:38
 */
@Repository
@Document(collection = "shared")
public class Shared {

    /** 共享id */
    private String shareId;
    /** 共享者 */
    private String userId;
    /** 被共享的文件或文件夹 */
    private String linkId;
    /** 共享密码 */
    private String sharePwd;
    /** 是否公开(公开则不需要密码) */
    private int isPublic;
    /** 被共享是否为文件, 1:文件, 2:文件夹 */
    private int isFile;
    /** 共享时间 */
    private Date shareTime;
    /** 共享状态, 1:正常, -1:已取消 */
    private int status;


    /** 常量: 共享状态: 正常 */
    public static final int STATUS_NORMAL = 1;
    /** 常量: 共享状态: 已取消 */
    public static final int STATUS_CANCEL = -1;
    /** 常量: 共享类型: 文件 */
    public static final int ISFILE_YES = 1;
    /** 常量: 共享类型: 文件夹 */
    public static final int ISFILE_NO = 2;

    public Shared() {
    }

    public Shared(String shareId, String userId, String linkId, String sharePwd, int isPublic, int isFile, Date shareTime, int status) {
        this.shareId = shareId;
        this.userId = userId;
        this.linkId = linkId;
        this.sharePwd = sharePwd;
        this.isPublic = isPublic;
        this.isFile = isFile;
        this.shareTime = shareTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Shared{" +
                "shareId='" + shareId + '\'' +
                ", userId='" + userId + '\'' +
                ", linkId='" + linkId + '\'' +
                ", sharePwd='" + sharePwd + '\'' +
                ", isPublic=" + isPublic +
                ", isFile=" + isFile +
                ", shareTime=" + shareTime +
                ", status=" + status +
                '}';
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getSharePwd() {
        return sharePwd;
    }

    public void setSharePwd(String sharePwd) {
        this.sharePwd = sharePwd;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public int getIsFile() {
        return isFile;
    }

    public void setIsFile(int isFile) {
        this.isFile = isFile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }
}
