package com.perfree.service;

import com.perfree.common.GetFileUtil;
import com.perfree.entity.FileResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件service
 */
@Service
public class FileService {

    /**
     * 获取一级目录
     * @param serverAddress
     * @return List<FileResult>
     */
    public List<FileResult> getParentFile(String serverAddress) {
        return GetFileUtil.getDirOrFileList(serverAddress,null);
    }

    /**
     * 获取指定目录
     * @param serverAddress
     * @param dir
     * @return List<FileResult>
     */
    public List<FileResult> getDirFile(String serverAddress, String dir) {
        return GetFileUtil.getDirOrFileList(serverAddress,dir);
    }
}
