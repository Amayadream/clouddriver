package com.amayadream.clouddriver.core.dao;

import com.amayadream.clouddriver.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Amayadream
 * @date :   2017-08-17 15:44
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    List<User> findUsersByAgeIsBetween(@Param("min") Integer min, @Param("max") Integer max);

}
