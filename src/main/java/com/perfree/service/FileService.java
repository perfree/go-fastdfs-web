package com.perfree.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.perfree.common.GetFileUtil;
import com.perfree.common.GoFastDfsApi;
import com.perfree.entity.FileResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    /**
     * 删除文件
     * @param peersUrl 服务地址
     * @param groupName 组名
     * @param path 路径
     * @return boolean
     */
    public boolean deleteFile(String peersUrl, String groupName, String path) {
        if (StrUtil.isNotBlank(groupName)){
            path = "/"+groupName+path;
        }else{
            path = "/group1" + path;
        }
        HashMap<String, Object> param = new HashMap<>(10);
        param.put("path",path);
        String result = HttpUtil.post(peersUrl + GoFastDfsApi.DELETE, param);
        JSONObject parseObj = JSONUtil.parseObj(result);
        if(parseObj.getStr("status").equals("ok")) {
            return true;
        }
        return false;
    }
}
