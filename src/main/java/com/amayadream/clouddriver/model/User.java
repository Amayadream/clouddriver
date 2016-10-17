package com.amayadream.clouddriver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

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
    /** 盐 */
    private String salt;
    /** 邮箱 */
    private String mail;
    /** 手机 */
    private String phone;

    public User() {
    }

    public User(String userId, String password, String salt, String mail, String phone) {
        this.userId = userId;
        this.password = password;
        this.salt = salt;
        this.mail = mail;
        this.phone = phone;
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
}
