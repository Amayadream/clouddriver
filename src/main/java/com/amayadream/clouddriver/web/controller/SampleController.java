package com.amayadream.clouddriver.web.controller;

import com.amayadream.clouddriver.common.result.ResultConstant;
import com.amayadream.clouddriver.common.result.ResultObject;
import com.amayadream.clouddriver.common.result.Results;
import com.amayadream.clouddriver.core.dao.UserRepository;
import com.amayadream.clouddriver.core.model.User;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : Amayadream
 * @date :   2017-08-17 15:54
 */
@RestController
@RequestMapping(value = "/")
public class SampleController {

    @Resource
    private UserRepository userRepository;

    @RequestMapping(value = "/pageable", method = RequestMethod.GET)
    public Results pageable(User user, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {

        Example<User> example = Example.of(user);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);

        Page<User> page = userRepository.findAll(example, pageable);

        ResultObject result = new ResultObject.Builder()
                .append("users", page)
                .build();

        return Results.ok(ResultConstant.SUCCESS, result);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Results list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<User> users = userRepository.findAll(sort);

        ResultObject result = new ResultObject.Builder()
                .append("users", users)
                .build();

        return Results.ok(ResultConstant.SUCCESS, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Results save(User user) {

        User u = userRepository.save(user);

        ResultObject result = new ResultObject.Builder()
                .append("user", u)
                .build();

        return Results.ok(ResultConstant.SUCCESS, result);
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public Results findByName(User user) {

        User u = userRepository.findByName(user.getName());

        ResultObject result = new ResultObject.Builder()
                .append("user", u)
                .build();

        return Results.ok(ResultConstant.SUCCESS, result);
    }

    @RequestMapping(value = "/findByNameAndAge", method = RequestMethod.GET)
    public Results findByNameAndAge(User user) {

        User u = userRepository.findByNameAndAge(user.getName(), user.getAge());

        ResultObject result = new ResultObject.Builder()
                .append("user", u)
                .build();

        return Results.ok(ResultConstant.SUCCESS, result);
    }

    @RequestMapping(value = "/findByAgeIsBetween", method = RequestMethod.GET)
    public Results findByAgeIsBetween(Integer min, Integer max) {

        List<User> u = userRepository.findUsersByAgeIsBetween(min, max);

        ResultObject result = new ResultObject.Builder()
                .append("user", u)
                .build();

        return Results.ok(ResultConstant.SUCCESS, result);
    }


}
