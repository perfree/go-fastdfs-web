package com.perfree.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.perfree.common.AjaxResult;
import com.perfree.common.DateUtil;
import com.perfree.common.GoFastDfsApi;
import com.perfree.entity.Peers;
import com.perfree.entity.User;
import com.perfree.mapper.PeersMapper;
import com.perfree.mapper.UserMapper;
import com.perfree.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 首页
 * @author Perfree
 *
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	private IndexService indexService;

	@Value("${version}")
	private String version;

	@Value("${version.date}")
	private String versionDate;

	@Autowired
	private PeersMapper peersMapper;

	@Autowired
	private UserMapper userMapper;

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
		Properties props=System.getProperties();
		model.addAttribute("osName",props.getProperty("os.name"));
		model.addAttribute("osArch",props.getProperty("os.arch"));
		model.addAttribute("version",version);
		model.addAttribute("versionDate", DateUtil.getFormatDate(DateUtil.StrToDate(versionDate,"yyyy-MM-dd"),"yyyy年MM月dd日"));
		return "main";
	}

	@RequestMapping("/main/getStat")
	@ResponseBody
	public AjaxResult getStat(){
		try {
			//获取文件信息,这一部分有待优化
			String string = HttpUtil.get(getPeersUrl()+GoFastDfsApi.STATUS);
			JSONObject parseObj = JSONUtil.parseObj(string);
			if(parseObj.get("status").equals("ok")) {
				Map<String, Object> result = indexService.getfileStat(parseObj.get("data"));
				return new AjaxResult(AjaxResult.AJAX_SUCCESS,result);
			}else{
				return new AjaxResult(AjaxResult.AJAX_ERROR,"调取go-fastdfs接口失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(AjaxResult.AJAX_ERROR,"系统异常");
	}

	/**
	 * 修正统计信息(30天)
	 * @return AjaxResult
	 */
	@RequestMapping("/main/repair_stat")
	@ResponseBody
	public AjaxResult repair_stat(){
		int count = 0;
		for (int i = 0; i > -30; i--){
			String dateStr = DateUtil.getFormatDate(DateUtil.StrToDate( DateUtil.dayAddOrCut(DateUtil.getFormatDate("yyyy-MM-dd"), i), "yyyy-MM-dd"), "yyyyMMdd");
			Map<String, Object> map = new HashMap<>(16);
			map.put("date", dateStr);
			String result = HttpUtil.post(getPeersUrl() + GoFastDfsApi.REPAIR_STAT, map);
			JSONObject parseObj = JSONUtil.parseObj(result);
			if(parseObj.get("status").equals("ok")) {
				count ++;
			}
		}
		return new AjaxResult(AjaxResult.AJAX_SUCCESS,"成功修正"+count+"天数据");
	}

	/**
	 * 删除空目录
	 * @return AjaxResult
	 */
	@RequestMapping("/main/remove_empty_dir")
	@ResponseBody
	public AjaxResult remove_empty_dir(){
		String result = HttpUtil.post(getPeersUrl()+GoFastDfsApi.REMOVE_EMPTY_DIR, new HashMap<>());
		JSONObject parseObj = JSONUtil.parseObj(result);
		if(parseObj.get("status").equals("ok")) {
			return new AjaxResult(AjaxResult.AJAX_ERROR,"操作成功,正在后台操作,请勿重复使用此功能");
		}else{
			return new AjaxResult(AjaxResult.AJAX_ERROR,"操作失败,请稍后再试");
		}
	}

	/**
	 * 备份元数据,30天
	 * @return
	 */
	@RequestMapping("/main/backup")
	@ResponseBody
	public AjaxResult backup(){
		int count = 0;
		for (int i = 0; i > -30; i--){
			String subDateStr = DateUtil.dayAddOrCut(DateUtil.getFormatDate("yyyy-MM-dd"), i);
			String dateStr = DateUtil.getFormatDate(DateUtil.StrToDate(subDateStr, "yyyy-MM-dd"), "yyyyMMdd");
			Map<String, Object> map = new HashMap<>(16);
			map.put("date", dateStr);
			String result = HttpUtil.post(getPeersUrl() + GoFastDfsApi.BACKUP, map);
			JSONObject parseObj = JSONUtil.parseObj(result);
			if(parseObj.get("status").equals("ok")) {
				count ++;
			}
		}
		return new AjaxResult(AjaxResult.AJAX_SUCCESS,"成功备份"+count+"天数据");
	}

	/**
	 * 同步失败修复
	 * @return AjaxResult
	 */
	@RequestMapping("/main/repair")
	@ResponseBody
	public AjaxResult repair(){
		String result = HttpUtil.post(getPeersUrl()+GoFastDfsApi.REPAIR+"?force=1", new HashMap<>());
		JSONObject parseObj = JSONUtil.parseObj(result);
		if(parseObj.get("status").equals("ok")) {
			return new AjaxResult(AjaxResult.AJAX_ERROR,"操作成功,正在后台操作,请勿重复使用此功能");
		}else{
			return new AjaxResult(AjaxResult.AJAX_ERROR,"操作失败,请稍后再试");
		}
	}

	/**
	 * 获取所有集群
	 * @return AjaxResult
	 */
	@RequestMapping("/main/getAllPeers")
	@ResponseBody
	public AjaxResult getAllPeers(){
		List<Peers> peers = peersMapper.getAllPeers();
		return new AjaxResult(AjaxResult.AJAX_SUCCESS,peers);
	}

	/**
	 * 切换集群
	 * @param id
	 * @return AjaxResult
	 */
	@RequestMapping("/main/switchPeers")
	@ResponseBody
	public AjaxResult switchPeers(int id, HttpSession session){
		if(id == getPeers().getId()){
			return new AjaxResult(AjaxResult.AJAX_ERROR,"当前正在使用此集群");
		}
		User user = new User();
		user.setPeersId(id);
		user.setId(getUser().getId());
		user.setUpdateTime(DateUtil.getFormatDate(new Date()));
		if(userMapper.switchPeers(user) > 0){
			Peers peers = peersMapper.getPeersById(id);
			session.setAttribute("peers",peers);
			return new AjaxResult(AjaxResult.AJAX_SUCCESS);
		}
		return new AjaxResult(AjaxResult.AJAX_ERROR,"切换失败");
	}
}
