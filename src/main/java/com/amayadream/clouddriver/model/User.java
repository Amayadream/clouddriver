package com.amayadream.clouddriver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 * @author :  Amayadream
 * @date :  2016.10.17 20:48
 */
@Document(collection = "user")
@Repository
public class User implements Serializable {

    /** 用户名 */
    @Id
    private String userId;
    /** 密码 */
    private String password;
    /** 盐, 随机生成 */
    private String salt;
    /** 邮箱 */
    private String mail;
    /** 手机 */
    private String phone;
    /** 注册时间 */
    private Date registerTime;
    /** 最后登陆时间 */
    private Date finallyTime;
    /** 账号状态 */
    private int status;

    /** 常量: 共享状态: 正常 */
    public static final int STATUS_NORMAL = 1;
    /** 常量: 共享状态: 已停用 */
    public static final int STATUS_FORB = -1;

    public User() {
    }

    public User(String userId, String password, String salt, String mail, String phone, int status) {
        this.userId = userId;
        this.password = password;
        this.salt = salt;
        this.mail = mail;
        this.phone = phone;
        this.status = status;
    }

    public User(String userId, String password, String salt, String mail, String phone, int status, Date registerTime, Date finallyTime) {
        this.userId = userId;
        this.password = password;
        this.salt = salt;
        this.mail = mail;
        this.phone = phone;
        this.status = status;
        this.registerTime = registerTime;
        this.finallyTime = finallyTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getFinallyTime() {
        return finallyTime;
    }

    public void setFinallyTime(Date finallyTime) {
        this.finallyTime = finallyTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
