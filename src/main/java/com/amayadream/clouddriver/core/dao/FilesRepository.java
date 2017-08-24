package com.amayadream.clouddriver.core.dao;

import com.amayadream.clouddriver.core.model.Files;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author :  Amayadream
 * @date :  2017.08.22 22:38
 */
public interface FilesRepository extends JpaRepository<Files, Long> {
    
    Files findByMd5(String fileMd5);

}
