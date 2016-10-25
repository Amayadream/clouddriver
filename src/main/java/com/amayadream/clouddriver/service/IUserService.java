package com.amayadream.clouddriver.service;

import com.amayadream.clouddriver.model.User;

/**
 * @author :  Amayadream
 * @date :  2016.10.17 20:59
 */
public interface IUserService {

    /**
     * 用户名是否存在
     */
    boolean isUserIdExist(String userId);

    /**
     * 手机号是否存在
     */
    boolean isPhoneExist(String phone);

    /**
     * 邮箱是否存在
     */
    boolean isMailExist(String mail);

    /**
     * 根据用户名查询指定用户
     */
    User findById(String userId);

    void insert(User user);

    void update(User user);

    int delete(String[] userIds);

}
