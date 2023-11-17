package com.perfree.controller;

import cn.hutool.core.util.StrUtil;
import com.perfree.common.Constant;
import com.perfree.common.ResponseBean;
import com.perfree.common.UploadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String index(Model model) {
        model.addAttribute("showAddress", getUploadShowUrl());
        return "file/upload";
    }

    @RequestMapping("/file/upload/moreFileUpload")
    @ResponseBody
    public ResponseBean moreFileUpload(@RequestParam("file") MultipartFile file, String scene, String path, String showUrl) {
        if (file.isEmpty()) {
            return ResponseBean.fail("请选择文件");
        }
        if (StringUtils.isBlank(scene)) {
            return ResponseBean.fail("请填写上传场景");
        }
        if (StrUtil.isBlank(showUrl)) {
            showUrl = getUploadShowUrl();
        }
        return UploadUtils.upload(file, getPeersUrl() + Constant.API_UPLOAD, showUrl,path);
    }
}
