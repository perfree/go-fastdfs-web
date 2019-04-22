package com.perfree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文件列表controller
 */
@Controller
public class FileController extends BaseController {

    /**
     * 文件列表
     * @return String
     */
    @RequestMapping("/file")
    public String index(){
        return "file/file";
    }

}
