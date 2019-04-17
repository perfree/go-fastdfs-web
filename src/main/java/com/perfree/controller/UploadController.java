package com.perfree.controller;

import cn.hutool.core.util.StrUtil;
import com.perfree.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传Controller
 */
@Controller
public class UploadController {
    /** server地址配置key */
    private static String PROPERTY_NAME = "go.fastdfs.server.address";

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Value("${upload.temp.path}")
    private String tempPath;

    @RequestMapping("/upload")
    public String index(){
        return "upload";
    }

    @RequestMapping("/upload/moreFileUpload")
    @ResponseBody
    public AjaxResult moreFileUpload(@RequestParam("file") MultipartFile file, String scene, String path,String showUrl){
        if(file.isEmpty()) {
            return new AjaxResult(AjaxResult.AJAX_ERROR,"请选择文件");
        }
        if(StringUtil.isBlank(scene)){
            return new AjaxResult(AjaxResult.AJAX_ERROR,"请填写上传场景");
        }
        String serverAddress = propertiesUtil.getProperty(PROPERTY_NAME);
        if(StrUtil.isBlank(showUrl)){
            showUrl = serverAddress;
        }
        return UploadUtils.upload(tempPath,serverAddress + GoFastDfsApi.UPLOAD, path, scene, file,showUrl);
    }
}
