package com.amayadream.clouddriver.core.dao;

import com.amayadream.clouddriver.core.model.Sharding;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author :  Amayadream
 * @date :  2017.08.22 23:15
 */
public interface ShardingRepository extends JpaRepository<Sharding, Long> {



}
