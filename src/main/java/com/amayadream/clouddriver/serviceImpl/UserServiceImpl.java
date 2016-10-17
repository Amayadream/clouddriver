package com.amayadream.clouddriver.serviceImpl;

import com.amayadream.clouddriver.model.User;
import com.amayadream.clouddriver.service.IUserService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author :  Amayadream
 * @date :  2016.10.17 20:59
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public boolean isUserIdExist(String userId) {
        User user = findById(userId);
        return user != null;
    }

    @Override
    public boolean isPhoneExist(String phone) {
        User user = mongoTemplate.findOne(Query.query(Criteria.where("phone").is(phone)), User.class);
        return user != null;
    }

    @Override
    public boolean isMailExist(String mail) {
        User user = mongoTemplate.findOne(Query.query(Criteria.where("mail").is(mail)), User.class);
        return user != null;
    }

    @Override
    public User findById(String userId) {
        return mongoTemplate.findById(userId, User.class);
    }


}
