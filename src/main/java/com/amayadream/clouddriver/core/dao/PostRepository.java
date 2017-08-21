package com.amayadream.clouddriver.core.dao;


import com.amayadream.clouddriver.core.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Amayadream
 * @date :   2017-08-18 14:26
 */
public interface PostRepository extends JpaRepository<Post, Long> {


}
