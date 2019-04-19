package com.perfree.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.perfree.common.AjaxResult;
import com.perfree.common.GoFastDfsApi;
import com.perfree.common.PageResult;
import com.perfree.common.RegexUtill;
import com.perfree.entity.Peers;
import com.perfree.service.PeersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 集群管理Controller
 */
@Controller
public class PeersController extends BaseController{

    @Autowired
    private PeersService peersService;

    /**
     * 集群列表
     * @return
     */
    @RequestMapping("/peers")
    public String index(){
        return "peers/list";
    }

    /**
     * 集群列表分页
     * @param page
     * @param limit
     * @return PageResult<Peers>
     */
    @RequestMapping("/peers/page")
    @ResponseBody
    public PageResult<Peers> page(int page, int limit){
        return peersService.page(page,limit);
    }

    /**
     * 添加集群页
     * @return String
     */
    @RequestMapping("/peers/add")
    public String add(){
        return "peers/add";
    }

    /**
     * 新增集群
     * @return AjaxResult
     */
    @RequestMapping("/peers/doAdd")
    @ResponseBody
    public AjaxResult doAdd(@Valid Peers peers, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new AjaxResult(AjaxResult.AJAX_ERROR,bindingResult.getFieldError().getDefaultMessage());
        }
        //校验服务地址
        if(!RegexUtill.verifyUrl(peers.getServerAddress())){
            return new AjaxResult(AjaxResult.AJAX_ERROR,"go-fastdfs服务地址填写错误!");
        }
        try{
            String string = HttpUtil.get(peers.getServerAddress()+ GoFastDfsApi.STAT);
            JSONObject parseObj = JSONUtil.parseObj(string);
            if(!parseObj.get("status").equals("ok")) {
                return new AjaxResult(AjaxResult.AJAX_ERROR,"测试连接go-fastdfs服务失败!请检查该服务地址是否已配置白名单!");
            }
        }catch(Exception e){
            return new AjaxResult(AjaxResult.AJAX_ERROR,"测试连接go-fastdfs服务失败!请检查该服务地址是否正确!");
        }
        //校验集群是否已经添加
        if(peersService.checkPeers(peers.getServerAddress())){
            return new AjaxResult(AjaxResult.AJAX_ERROR,"该集群已存在!");
        }
        //添加
        if(peersService.addPeers(peers)){
            return new AjaxResult(AjaxResult.AJAX_SUCCESS);
        }
        return new AjaxResult(AjaxResult.AJAX_ERROR,"系统异常");
    }

    /**
     * 删除集群
     * @param id
     * @return AjaxResult
     */
    @RequestMapping("/peers/del")
    @ResponseBody
    public AjaxResult delPeersById(int id){
        if(id == getPeers().getId()){
            return new AjaxResult(AjaxResult.AJAX_ERROR,"删除失败,该集群正在使用,请切换至其它集群再删除");
        }
        if(peersService.delPeersById(id)){
            return new AjaxResult(AjaxResult.AJAX_SUCCESS);
        }
        return new AjaxResult(AjaxResult.AJAX_ERROR,"删除失败");
    }

    /**
     * 前往编辑页面
     * @return
     */
    @RequestMapping("/peers/edit")
    public String edit(int id, Model model){
        Peers peers = peersService.getPeersById(id);
        model.addAttribute("peers",peers);
        return "peers/edit";
    }

    /**
     * 更新集群
     * @param peers
     * @param bindingResult
     * @return AjaxResult
     */
    @RequestMapping("/peers/doEdit")
    @ResponseBody
    public AjaxResult doEdit(@Valid Peers peers, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new AjaxResult(AjaxResult.AJAX_ERROR,bindingResult.getFieldError().getDefaultMessage());
        }
        //校验服务地址
        if(!RegexUtill.verifyUrl(peers.getServerAddress())){
            return new AjaxResult(AjaxResult.AJAX_ERROR,"go-fastdfs服务地址填写错误!");
        }
        try{
            String string = HttpUtil.get(peers.getServerAddress()+ GoFastDfsApi.STAT);
            JSONObject parseObj = JSONUtil.parseObj(string);
            if(!parseObj.get("status").equals("ok")) {
                return new AjaxResult(AjaxResult.AJAX_ERROR,"测试连接go-fastdfs服务失败!请检查该服务地址是否已配置白名单!");
            }
        }catch(Exception e){
            return new AjaxResult(AjaxResult.AJAX_ERROR,"测试连接go-fastdfs服务失败!请检查该服务地址是否正确!");
        }
        //校验集群是否已经添加
        if(peersService.checkPeers(peers.getServerAddress())){
            return new AjaxResult(AjaxResult.AJAX_ERROR,"该集群已存在!");
        }
        //更新
        if(peersService.updatePeers(peers)){
            return new AjaxResult(AjaxResult.AJAX_SUCCESS);
        }
        return new AjaxResult(AjaxResult.AJAX_ERROR,"系统异常");
    }
}
