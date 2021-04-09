package com.perfree.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.perfree.common.Constant;
import com.perfree.common.PageResult;
import com.perfree.common.RegexUtill;
import com.perfree.common.ResponseBean;
import com.perfree.model.Peers;
import com.perfree.service.PeersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 集群管理Controller
 */
@Controller
public class PeersController extends BaseController {

    @Autowired
    private PeersService peersService;

    /**
     * @return java.lang.String
     * @description 集群列表
     * @author Perfree
     */
    @RequestMapping("/peers")
    public String index() {
        return "peers/list";
    }

    /**
     * @param page  page
     * @param limit limit
     * @return com.perfree.common.PageResult<com.perfree.model.Peers>
     * @description 集群列表分页
     * @author Perfree
     */
    @RequestMapping("/peers/page")
    @ResponseBody
    public PageResult<Peers> page(int page, int limit) {
        return peersService.listPage(page, limit);
    }

    /**
     * 添加集群页
     *
     * @return String
     */
    @RequestMapping("/peers/add")
    public String add() {
        return "peers/add";
    }

    /**
     * 新增集群
     *
     * @return AjaxResult
     */
    @RequestMapping("/peers/doAdd")
    @ResponseBody
    public ResponseBean doAdd(@Valid Peers peers, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseBean.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        // 校验服务地址
        if (!RegexUtill.verifyUrl(peers.getServerAddress())) {
            return ResponseBean.fail("go-fastdfs服务地址填写错误!");
        }
        try {
            String urlPath = peers.getServerAddress();
            if (StrUtil.isNotBlank(peers.getGroupName())) {
                urlPath += "/" + peers.getGroupName();
            }
            String string = HttpRequest.get(urlPath + Constant.API_STAT).timeout(2000).execute().body();
            JSONObject parseObj = JSONUtil.parseObj(string);
            if (!parseObj.get("status").equals("ok")) {
                return ResponseBean.fail("测试连接go-fastdfs服务失败!请检查该服务地址是否已配置白名单!");
            }
        } catch (Exception e) {
            return ResponseBean.fail("测试连接go-fastdfs服务失败!请检查该服务地址是否正确!");
        }
        // 校验集群是否已经添加
        if (peersService.checkPeers(peers.getServerAddress())) {
            return ResponseBean.fail("该集群已存在!");
        }
        // 添加
        if (peersService.addPeers(peers)) {
            return ResponseBean.success();
        }
        return ResponseBean.fail("系统异常");
    }

    /**
     * 删除集群
     *
     * @param id
     * @return AjaxResult
     */
    @RequestMapping("/peers/del")
    @ResponseBody
    public ResponseBean delPeersById(int id) {
        if (id == getPeers().getId()) {
            return ResponseBean.fail("删除失败,该集群正在使用,请切换至其它集群再删除");
        }
        if (peersService.delPeersById(id)) {
            return ResponseBean.success();
        }
        return ResponseBean.fail("删除失败");
    }

    /**
     * 前往编辑页面
     *
     * @return String
     */
    @RequestMapping("/peers/edit")
    public String edit(int id, Model model) {
        Peers peers = peersService.getById(id);
        model.addAttribute("peers", peers);
        return "peers/edit";
    }

    /**
     * 更新集群
     *
     * @param peers         peers
     * @param bindingResult bindingResult
     * @return ResponseBean
     */
    @RequestMapping("/peers/doEdit")
    @ResponseBody
    public ResponseBean doEdit(@Valid Peers peers, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return ResponseBean.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        //校验服务地址
        if (!RegexUtill.verifyUrl(peers.getServerAddress())) {
            return ResponseBean.fail("go-fastdfs服务地址填写错误!");
        }
        try {
            String urlPath = peers.getServerAddress();
            if (StrUtil.isNotBlank(peers.getGroupName())) {
                urlPath += "/" + peers.getGroupName();
            }
            String string = HttpUtil.get(urlPath + Constant.API_STAT);
            JSONObject parseObj = JSONUtil.parseObj(string);
            if (!parseObj.get("status").equals("ok")) {
                return ResponseBean.fail("测试连接go-fastdfs服务失败!请检查该服务地址是否已配置白名单!");
            }
        } catch (Exception e) {
            return ResponseBean.fail("测试连接go-fastdfs服务失败!请检查该服务地址是否正确!");
        }
        //校验集群是否已经添加
        if (!peersService.getById(peers.getId()).getServerAddress().equals(peers.getServerAddress())
                && peersService.checkPeers(peers.getServerAddress())) {
            return ResponseBean.fail("该集群已存在!");
        }
        //更新
        if (peersService.updateById(peers)) {
            if (peers.getId() == getPeers().getId()) {
                session.setAttribute("peers", peersService.getById(peers.getId()));
            }
            return ResponseBean.success();
        }
        return ResponseBean.fail("系统异常");
    }
}
