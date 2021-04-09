package com.perfree.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import com.perfree.common.Constant;
import com.perfree.common.DateUtil;
import com.perfree.common.ResponseBean;
import com.perfree.model.Peers;
import com.perfree.model.User;
import com.perfree.service.IndexService;
import com.perfree.service.PeersService;
import com.perfree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Perfree
 * @description 首页相关
 * @date 2021/3/22 16:14
 */
@Controller
public class IndexController extends BaseController {
    @Value("${version}")
    private String version;

    @Value("${version.date}")
    private String versionDate;

    @Autowired
    private IndexService indexService;
    @Autowired
    private PeersService peersService;
    @Autowired
    private UserService userService;

    /**
     * @return java.lang.String
     * @description 首页
     * @author Perfree
     */
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("user", getUser());
        return "index";
    }

    /**
     * @return java.lang.String
     * @description 首页
     * @author Perfree
     */
    @RequestMapping("/home")
    public String home(Model model) {
        OsInfo osInfo = SystemUtil.getOsInfo();
        model.addAttribute("osName", osInfo.getName());
        model.addAttribute("osArch", osInfo.getArch());
        model.addAttribute("version", version);
        model.addAttribute("versionDate", versionDate);
        return "home";
    }

    /**
     * @return com.perfree.common.ResponseBean
     * @description 获取状态信息
     * @author Perfree
     */
    @RequestMapping("/home/getStatus")
    @ResponseBody
    public ResponseBean getStatus() {
        try {
            String json = HttpUtil.get(getPeersUrl() + Constant.API_STATUS);
            JSONObject parseObj = JSONUtil.parseObj(json);
            if (parseObj.get("status").equals(Constant.API_STATUS_SUCCESS)) {
                Map<String, Object> result = indexService.getStatus(parseObj.get("data"));
                return ResponseBean.success(result);
            } else {
                return ResponseBean.fail("调取go-fastdfs接口失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBean.fail("系统异常");
    }

    /**
     * @return com.perfree.common.ResponseBean
     * @description 修正统计信息(30天)
     * @author Perfree
     */
    @RequestMapping("/home/repair_stat")
    @ResponseBody
    public ResponseBean repair_stat() {
        int count = 0;
        for (int i = 0; i > -30; i--) {
            String dateStr = DateUtil.getFormatDate(DateUtil.StrToDate(DateUtil.dayAddOrCut(DateUtil.getFormatDate("yyyy-MM-dd"), i), "yyyy-MM-dd"), "yyyyMMdd");
            Map<String, Object> map = new HashMap<>(16);
            map.put("date", dateStr);
            String result = HttpUtil.post(getPeersUrl() + Constant.API_REPAIR_STAT, map);
            JSONObject parseObj = JSONUtil.parseObj(result);
            if (parseObj.get("status").equals("ok")) {
                count++;
            }
        }
        return ResponseBean.success("成功修正" + count + "天数据", null);
    }

    /**
     * @return com.perfree.common.ResponseBean
     * @description 删除空目录
     * @author Perfree
     */
    @RequestMapping("/home/remove_empty_dir")
    @ResponseBody
    public ResponseBean remove_empty_dir() {
        String result = HttpUtil.post(getPeersUrl() + Constant.API_REMOVE_EMPTY_DIR, new HashMap<>());
        JSONObject parseObj = JSONUtil.parseObj(result);
        if (parseObj.get("status").equals(Constant.API_STATUS_SUCCESS)) {
            return ResponseBean.success("操作成功,正在后台操作,请勿重复使用此功能", null);
        } else {
            return ResponseBean.fail("操作失败,请稍后再试");
        }
    }

    /**
     * @return AjaxResult
     * @description 备份元数据, 30天
     * @author Perfree
     */
    @RequestMapping("/home/backup")
    @ResponseBody
    public ResponseBean backup() {
        int count = 0;
        for (int i = 0; i > -30; i--) {
            String subDateStr = DateUtil.dayAddOrCut(DateUtil.getFormatDate("yyyy-MM-dd"), i);
            String dateStr = DateUtil.getFormatDate(DateUtil.StrToDate(subDateStr, "yyyy-MM-dd"), "yyyyMMdd");
            Map<String, Object> map = new HashMap<>(16);
            map.put("date", dateStr);
            String result = HttpUtil.post(getPeersUrl() + Constant.API_BACKUP, map);
            JSONObject parseObj = JSONUtil.parseObj(result);
            if (parseObj.get("status").equals(Constant.API_STATUS_SUCCESS)) {
                count++;
            }
        }
        return ResponseBean.success("成功备份" + count + "天数据", null);
    }

   /**
    * @description 同步失败修复
    * @return com.perfree.common.ResponseBean
    * @author Perfree
    */
    @RequestMapping("/home/repair")
    @ResponseBody
    public ResponseBean repair() {
        String result = HttpUtil.post(getPeersUrl() + Constant.API_REPAIR + "?force=1", new HashMap<>());
        JSONObject parseObj = JSONUtil.parseObj(result);
        if (parseObj.get("status").equals(Constant.API_STATUS_SUCCESS)) {
            return ResponseBean.success("操作成功,正在后台操作,请勿重复使用此功能", null);
        } else {
            return ResponseBean.fail("操作失败,请稍后再试");
        }
    }

   /**
    * @description 获取所有集群
    * @return com.perfree.common.ResponseBean
    * @author Perfree
    */
    @RequestMapping("/home/getAllPeers")
    @ResponseBody
    public ResponseBean getAllPeers(){
        List<Peers> peers = peersService.list();
        return ResponseBean.success(peers);
    }

    /**
     * @description 切换集群
     * @param id id
     * @param session session
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    @RequestMapping("/home/switchPeers")
    @ResponseBody
    public ResponseBean switchPeers(int id, HttpSession session){
        if(id == getPeers().getId()){
            return ResponseBean.fail("当前正在使用此集群");
        }
        User user = new User();
        user.setPeersId(id);
        user.setId(getUser().getId());
        user.setUpdateTime(new Date());
        if(userService.updateById(user)){
            Peers peers = peersService.getById(id);
            session.setAttribute("peers",peers);
            return ResponseBean.success();
        }
        return ResponseBean.fail("切换失败");
    }

}
