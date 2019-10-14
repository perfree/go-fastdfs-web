package com.perfree.controller;

import cn.hutool.core.util.StrUtil;
import com.perfree.common.AjaxResult;
import com.perfree.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @Author Perfree
 * @Date 10:58 2019/5/11
 * 文件列表controller
 */
@Controller
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

    /**
     * 文件列表
     *
     * @return String
     */
    @RequestMapping("/file")
    public String index(Model model) {
        return "file/file";
    }

    /**
     * 获取一级目录
     *
     * @return AjaxResult
     */
    @RequestMapping("/file/getParentFile")
    @ResponseBody
    public AjaxResult getParentFile() {
        return new AjaxResult(AjaxResult.AJAX_SUCCESS, fileService.getParentFile(getPeersGroupName(),getPeersUrl()));
    }

    /**
     * 指定目录获取
     *
     * @param dir
     * @return AjaxResult
     */
    @RequestMapping("/file/getDirFile")
    @ResponseBody
    public AjaxResult getDirFile(String dir) {
        return new AjaxResult(AjaxResult.AJAX_SUCCESS, fileService.getDirFile(getShowUrl(),getPeersUrl(), dir));
    }

    /**
     * 删除文件
     *
     * @param md5
     * @return AjaxResult
     */
    @RequestMapping("/file/deleteFile")
    @ResponseBody
    public AjaxResult deleteFile(String md5) {
        if (fileService.deleteFile(getPeersUrl(),md5)) {
            return new AjaxResult(AjaxResult.AJAX_SUCCESS);
        }
        return new AjaxResult(AjaxResult.AJAX_ERROR, "删除失败");
    }

    /**
     * 文件信息
     *
     * @param md5
     * @return AjaxResult
     */
    @RequestMapping("/file/details")
    @ResponseBody
    public AjaxResult details(String md5) {
        return fileService.details(getPeersUrl(),md5);
    }

    /**
     * 下载文件
     * @param path
     * @param name
     * @param request
     * @param response
     */
    @RequestMapping("/file/downloadFile")
    @ResponseBody
    public void downloadFile(String path, String name, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        response.setContentType("application/octet-stream");
        BufferedInputStream in = null;
        try {
            URL url = new URL(getPeersUrl()+"/"+path+"/"+name);
            in = new BufferedInputStream(url.openStream());
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            // 将网络输入流转换为输出流
            int i;
            while ((i = in.read()) != -1) {
                response.getOutputStream().write(i);
            }
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
               if (in != null){
                   in.close();
               }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
