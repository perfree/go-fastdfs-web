package com.perfree.controller;

import com.perfree.common.AjaxResult;
import com.perfree.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文件列表controller
 */
@Controller
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

    /**
     * 文件列表
     * @return String
     */
    @RequestMapping("/file")
    public String index(){
        return "file/file";
    }

    /**
     * 获取一级目录
     * @return AjaxResult
     */
    @RequestMapping("/file/getParentFile")
    @ResponseBody
    public AjaxResult getParentFile(){
       return new AjaxResult(AjaxResult.AJAX_SUCCESS,fileService.getParentFile(getPeersUrl()));
    }

    /**
     * 指定目录获取
     * @param dir
     * @return AjaxResult
     */
    @RequestMapping("/file/getDirFile")
    @ResponseBody
    public AjaxResult getDirFile(String dir){
        fileService.getDirFile(getPeersUrl(),dir);
        return new AjaxResult(AjaxResult.AJAX_SUCCESS,fileService.getDirFile(getPeersUrl(),dir));
    }

}
