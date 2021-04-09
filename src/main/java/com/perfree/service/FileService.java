package com.perfree.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.perfree.common.*;
import com.perfree.form.FileDetails;
import com.perfree.form.FileResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @description 文件列表相关逻辑处理
 * @author Perfree
 * @date 2021/4/7 11:50
 */
@Service
public class FileService {

   /**
    * @description 获取一级目录
    * @param peersGroupName peersGroupName
    * @param serverAddress serverAddress
    * @return java.util.List<com.perfree.form.FileResult>
    * @author Perfree
    */
    public List<FileResult> getParentFile(String peersGroupName, String serverAddress) {
        return GetFileUtil.getDirOrFileList(peersGroupName,serverAddress,null);
    }

    /**
     * @description 获取指定目录
     * @param showUrl showUrl
     * @param serverAddress serverAddress
     * @param dir dir
     * @return java.util.List<com.perfree.form.FileResult>
     * @author Perfree
     */
    public List<FileResult> getDirFile(String showUrl, String serverAddress, String dir) {
        return GetFileUtil.getDirOrFileList(showUrl,serverAddress,dir);
    }

  /**
   * @description 删除文件
   * @param peersUrl peersUrl
   * @param md5 md5
   * @return boolean
   * @author Perfree
   */
    public boolean deleteFile(String peersUrl, String md5) {
        HashMap<String, Object> param = new HashMap<>(10);
        param.put("md5",md5);
        JSONObject parseObj = JSONUtil.parseObj(HttpUtil.post(peersUrl + Constant.API_DELETE, param));
        return parseObj.getStr("status").equals(Constant.API_STATUS_SUCCESS);
    }

    /**
     * @description 获取文件信息
     * @param peersUrl peersUrl
     * @param md5 md5
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    public ResponseBean details(String peersUrl, String md5) {
        HashMap<String, Object> param = new HashMap<>(10);
        param.put("md5",md5);
        JSONObject parseObj = JSONUtil.parseObj(HttpUtil.post(peersUrl + Constant.API_GET_FILE_INFO, param));
        if(parseObj.getStr("status").equals(Constant.API_STATUS_SUCCESS)) {
            FileDetails fileDetails = JSONUtil.toBean(parseObj.getStr("data"), FileDetails.class);
            fileDetails.setSize(FileSizeUtil.GetLength(Long.parseLong(fileDetails.getSize())));
            fileDetails.setTimeStamp(DateUtil.getFormatDate(new Date(Long.parseLong(fileDetails.getTimeStamp())* 1000)));
            return ResponseBean.success(fileDetails);
        }
        return ResponseBean.fail("获取文件信息失败");
    }
}
