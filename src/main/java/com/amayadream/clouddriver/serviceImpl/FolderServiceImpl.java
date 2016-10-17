package com.amayadream.clouddriver.serviceImpl;

import com.amayadream.clouddriver.exception.FolderNotFoundException;
import com.amayadream.clouddriver.model.FileCommon;
import com.amayadream.clouddriver.model.FolderCommon;
import com.amayadream.clouddriver.service.IFileService;
import com.amayadream.clouddriver.service.IFolderService;
import com.amayadream.clouddriver.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author :  Amayadream
 * @date :  2016.09.26 13:31
 */
@Service
public class FolderServiceImpl implements IFolderService {

    @Resource
    private MongoTemplate mongoTemplate;


    public FolderCommon findById(String userId, String folderId) {
        FolderCommon folder = mongoTemplate.findById(folderId, FolderCommon.class);
        return folder != null && !StringUtils.isEmpty(folder.getUserId()) && folder.getUserId().equals(userId) ? folder : null;
    }

    public List<FolderCommon> find(String userId, String folderPid, String search, boolean isEquals) {
        Criteria criteria = Criteria.where("userId").is(userId).and("folderPid").is(folderPid);
        if (!StringUtils.isEmpty(search))
            if(isEquals)
                criteria.and("folderName").is(search);
            else
                criteria.and("folderName").regex(search);
        return mongoTemplate.find(Query.query(criteria), FolderCommon.class);
    }

    public int validateFolderName(String userId, String folderId, String folderName) throws FolderNotFoundException {
        List<FolderCommon> list = find(userId, folderId, folderName, true);
        return list.size() == 0 ? 1 : 0;
    }

    public int add(String userId, String folderPid, String folderName) throws FolderNotFoundException {
        if(validateFolderName(userId, folderPid, folderName) == 1){
            FolderCommon folder = new FolderCommon();
            Date time = new Date();
            folder.setFolderId(StringUtil.generateGuid());
            folder.setFolderPid(folderPid);
            folder.setFolderName(folderName);
            folder.setUserId(userId);
            folder.setCreateTime(time);
            folder.setModifyTime(time);
            folder.setStatus(1);
            mongoTemplate.save(folder);
            return 1;
        }else{
            return -1;
        }
    }

    public int update(String userId, String folderId, String folderName) throws FolderNotFoundException {
        FolderCommon folder = findById(userId, folderId);
        if(folder == null) throw new FolderNotFoundException();
        folder.setFolderName(folderName);
        folder.setModifyTime(new Date());
        mongoTemplate.save(folder);
        return 1;
    }

    public int remove(String userId, String[] folderId) {
        return 0;
    }
}
