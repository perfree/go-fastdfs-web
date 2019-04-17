package com.perfree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文件上传Controller
 */
@Controller
public class UploadController {

    @RequestMapping("/upload")
    public String index(){
        return "upload";
    }
}
