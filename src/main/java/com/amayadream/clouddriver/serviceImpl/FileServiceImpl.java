package com.amayadream.clouddriver.serviceImpl;

import com.amayadream.clouddriver.exception.FileCommonNotFoundException;
import com.amayadream.clouddriver.model.FileCommon;
import com.amayadream.clouddriver.service.IFileService;
import com.amayadream.clouddriver.utils.Constants;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author :  Amayadream
 * @date :  2016.09.26 13:31
 */
@Service
public class FileServiceImpl implements IFileService {

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private GridFsTemplate gridFsTemplate;
    @Resource
    private FileCommon fileCommon = new FileCommon();


    public FileCommon findById(String userId, String fileId) {
        FileCommon file = mongoTemplate.findById(userId, FileCommon.class);
        return !StringUtils.isEmpty(file.getUserId()) && file.getUserId().equals(userId) ? file : null;
    }

    public List<FileCommon> find(String userId, String folderId, String search) {
        Criteria criteria = Criteria.where("userId").is(userId).and("folderId").is(folderId);
        return mongoTemplate.find(Query.query(criteria), FileCommon.class);
    }

    public void insert(FileCommon fileCommon) {
        mongoTemplate.save(fileCommon);
    }

    public void rename(String userId, String fileId, String fileName) throws FileCommonNotFoundException {
        FileCommon file = findById(userId, fileId);
        if(file != null){
            file.setFileName(fileName);
            file.setFileExt(FilenameUtils.getExtension(fileName));
            file.setModifyTime(new Date());
            mongoTemplate.save(file);
        }else{
            throw new FileCommonNotFoundException(Constants.EXCEPTION_MSG_FILECOMMON_NOT_FOUND);
        }
    }

    public int remove(String userId, String[] fileIds) {
        int i = 0;
        for (String fileId : fileIds) {
            fileCommon = new FileCommon();
            fileCommon = findById(userId, fileId);
            if(fileCommon != null)
                mongoTemplate.remove(fileCommon);
            else
                i++;
        }
        return i;
    }
}
