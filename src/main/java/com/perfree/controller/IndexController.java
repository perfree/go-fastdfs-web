package com.perfree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perfree.common.FileSizeUtil;
import com.perfree.common.GoFastDfsApi;
import com.perfree.common.PropertiesUtil;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * 首页
 * @author Perfree
 *
 */
@Controller
public class IndexController {

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * 控制台
	 * @return
	 */
	@RequestMapping("/main")
	public String main(Model model) {
		String serverAddress = new PropertiesUtil().getProperty("go.fastdfs.server.address");
		try {
			//获取文件信息
			String string = HttpUtil.get(serverAddress+GoFastDfsApi.STAT);
			JSONObject parseObj = JSONUtil.parseObj(string);
			if(parseObj.get("status").equals("ok")) {
				JSONArray parseArray = JSONUtil.parseArray(parseObj.get("data"));
				long fileSize = 0;
				long fileCount = 0;
				for (int i = 0;i < parseArray.size();i++) {
					JSONObject fileStats = JSONUtil.parseObj(parseArray.getStr(i));
					if(fileStats.get("date").equals("all")) {
						model.addAttribute("size", FileSizeUtil.GetLength(Long.valueOf(fileStats.getStr("totalSize"))));
						model.addAttribute("count", fileStats.getStr("fileCount"));
					}else {
						fileSize += Long.valueOf(fileStats.getStr("totalSize"));
						fileCount += Long.valueOf(fileStats.getStr("fileCount"));
					}
				}
				model.addAttribute("latelySize",FileSizeUtil.GetLength(fileSize));
				model.addAttribute("latelyCount",fileCount);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "main";
	}
}
