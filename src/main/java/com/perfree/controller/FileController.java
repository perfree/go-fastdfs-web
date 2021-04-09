package com.perfree.controller;

import com.perfree.common.ResponseBean;
import com.perfree.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @description 文件列表相关controller
 * @author Perfree
 * @date 2021/4/7 11:53
 */
@Controller
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

   /**
    * @description 文件列表
    * @return java.lang.String
    * @author Perfree
    */
    @RequestMapping("/file")
    public String index() {
        return "file/file";
    }

   /**
    * @description 获取一级目录
    * @return com.perfree.common.ResponseBean
    * @author Perfree
    */
    @RequestMapping("/file/getParentFile")
    @ResponseBody
    public ResponseBean getParentFile() {
        return ResponseBean.success(fileService.getParentFile(getPeersGroupName(),getPeersUrl()));
    }

    /**
     * @description 指定目录获取
     * @param dir  dir
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    @RequestMapping("/file/getDirFile")
    @ResponseBody
    public ResponseBean getDirFile(String dir) {
        return ResponseBean.success(fileService.getDirFile(getShowUrl(),getPeersUrl(), dir));
    }

    /**
     * @description 删除文件
     * @param md5  md5
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    @RequestMapping("/file/deleteFile")
    @ResponseBody
    public ResponseBean deleteFile(String md5) {
        if (fileService.deleteFile(getPeersUrl(),md5)) {
            return ResponseBean.success();
        }
        return ResponseBean.fail("删除失败");
    }

    /**
     * @description 文件信息
     * @param md5  md5
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    @RequestMapping("/file/details")
    @ResponseBody
    public ResponseBean details(String md5) {
        return fileService.details(getPeersUrl(),md5);
    }

   /**
    * @description 下载文件
    * @param path path
    * @param name name
    * @param request request
    * @param response response
    * @author Perfree
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
