package com.perfree.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.perfree.common.AjaxResult;
import com.perfree.entity.FileResult;
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
import java.util.List;

/**
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
    public String index() {
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
        return new AjaxResult(AjaxResult.AJAX_SUCCESS, fileService.getParentFile(getPeersUrl()));
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
        return new AjaxResult(AjaxResult.AJAX_SUCCESS, fileService.getDirFile(getPeersUrl(), dir));
    }

    /**
     * 删除文件
     *
     * @param path
     * @return AjaxResult
     */
    @RequestMapping("/file/deleteFile")
    @ResponseBody
    public AjaxResult deleteFile(String path) {
        if (fileService.deleteFile(getPeers().getServerAddress(), getPeersGroupName(), path)) {
            return new AjaxResult(AjaxResult.AJAX_SUCCESS);
        }
        return new AjaxResult(AjaxResult.AJAX_ERROR, "删除失败");
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
        String peersUrl = getPeers().getServerAddress();
        if(StrUtil.isNotBlank(getPeersGroupName())){
            peersUrl += "/"+getPeersGroupName();
        }else {
            peersUrl += "/group1";
        }
        BufferedInputStream in = null;
        try {
            URL url = new URL(peersUrl+path+"/"+name);
            in = new BufferedInputStream(url.openStream());
            response.reset();
            response.setContentType("application/octet-stream");
            String os = System.getProperty("os.name");
            if(os.toLowerCase().indexOf("windows") != -1){
                name = new String(name.getBytes("GBK"), "ISO-8859-1");
            }else{
                //判断浏览器
                String userAgent = request.getHeader("User-Agent").toLowerCase();
                if(userAgent.indexOf("msie") > 0){
                    name = URLEncoder.encode(name, "ISO-8859-1");
                }
            }
            response.setHeader("Content-Disposition","attachment;filename=" + name);
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
