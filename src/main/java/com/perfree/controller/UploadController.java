package com.perfree.controller;

import cn.hutool.core.util.StrUtil;
import com.perfree.common.AjaxResult;
import com.perfree.common.GoFastDfsApi;
import com.perfree.common.StringUtil;
import com.perfree.common.UploadUtils;
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
public class UploadController extends BaseController {

    @Value("${upload.temp.path}")
    private String tempPath;

    @RequestMapping("/file/upload")
    public String index(){
        return "file/upload";
    }

    @RequestMapping("/file/upload/moreFileUpload")
    @ResponseBody
    public AjaxResult moreFileUpload(@RequestParam("file") MultipartFile file, String scene, String path,String showUrl){
        if(file.isEmpty()) {
            return new AjaxResult(AjaxResult.AJAX_ERROR,"请选择文件");
        }
        if(StringUtil.isBlank(scene)){
            return new AjaxResult(AjaxResult.AJAX_ERROR,"请填写上传场景");
        }
        if(StrUtil.isBlank(showUrl)){
            showUrl = getPeers().getServerAddress();
        }
        return UploadUtils.upload(tempPath,getPeersUrl() + GoFastDfsApi.UPLOAD, path, scene, file,showUrl);
    }
}
