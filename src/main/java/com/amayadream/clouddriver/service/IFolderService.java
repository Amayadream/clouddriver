package com.amayadream.clouddriver.service;

import com.amayadream.clouddriver.exception.FolderNotFoundException;
import com.amayadream.clouddriver.model.FolderCommon;

import java.util.List;

/**
 * @author :  Amayadream
 * @date :  2016.09.26 13:31
 */
public interface IFolderService {

    FolderCommon findById(String userId, String folderId);

    List<FolderCommon> find(String userId, String folderPid, String search, boolean isEquals);

    int validateFolderName(String userId, String folderId, String folderName) throws FolderNotFoundException;

    void insert(String userId, String folderPid, String folderName) throws Exception;

    void rename(String userId, String folderId, String folderName) throws FolderNotFoundException;

    int remove(String userId, String[] folderId);

}
