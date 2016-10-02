package com.amayadream.clouddriver.serviceImpl;

import com.amayadream.clouddriver.model.FileLibrary;
import com.amayadream.clouddriver.service.IFileLibraryService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author :  Amayadream
 * @date :  2016.09.30 16:28
 */
@Service
public class FileLibraryServiceImpl implements IFileLibraryService {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 判断文件是否存在于文件库
     */
    public boolean isExist(String fileMd5) {
        FileLibrary lib = mongoTemplate.findById(fileMd5, FileLibrary.class);
        return lib != null;
    }

    public void insert(FileLibrary fileLibrary) {
        mongoTemplate.save(fileLibrary);
    }


}
