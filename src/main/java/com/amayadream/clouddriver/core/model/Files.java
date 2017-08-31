package com.amayadream.clouddriver.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 文件实体
 * @author :  Amayadream
 * @date :  2017.08.22 21:09
 */
@Entity
public class Files implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    /* 文件名, 包含后缀 */
    private String name;
    /* 文件格式 */
    private String suffix;
    /* 文件大小, 单位为kb */
    private Long size;
    /* 文件路径, 相对路径 */
    private String path;
    /* 文件md5 */
    private String md5;
    /* 文件状态, 0:待分片上传, 1:正常 */
    private Integer status;
    /* 创建时间 */
    private LocalDateTime createdTime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
