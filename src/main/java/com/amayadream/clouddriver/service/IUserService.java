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

    boolean isPhoneExist(String phone);

    boolean isMailExist(String mail);

    User findById(String userId);

}
