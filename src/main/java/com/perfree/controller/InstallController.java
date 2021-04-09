package com.perfree.controller;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.perfree.common.Constant;
import com.perfree.common.ResponseBean;
import com.perfree.form.ApiResult;
import com.perfree.form.GoFastDfsCfg;
import com.perfree.form.InstallForm;
import com.perfree.model.Peers;
import com.perfree.model.User;
import com.perfree.service.PeersService;
import com.perfree.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description 安装
 * @author Perfree
 * @date 2021/3/23 9:22
 */
@Controller
public class InstallController extends BaseController {
    @Autowired
    private PeersService peersService;
    @Autowired
    private UserService userService;

    /**
     * @description 安装页
     * @return java.lang.String
     * @author Perfree
     */
    @RequestMapping("/install")
    public String index() {
        return "install";
    }
    
    /** 
     * @description 检查本机是否安装了fastDfs
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */ 
    @RequestMapping("/install/checkLocalServer")
    @ResponseBody
    public ResponseBean checkLocalServer() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("action", "get");
        String result;
        try{
            result = HttpRequest.post("http://127.0.0.1:8080/group1" + Constant.API_RELOAD).form(paramMap).timeout(1000).execute().body();
        } catch (Exception e) {
            try{
                result = HttpRequest.post("http://127.0.0.1:8080" + Constant.API_RELOAD).form(paramMap).timeout(1000).execute().body();
            } catch (Exception eq) {
                return ResponseBean.fail("本机未安装goFastDfs");
            }
        }
        if (StringUtils.isBlank(result)) {
            return ResponseBean.fail("本机未安装goFastDfs");
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.getString("status").equals("ok")) {
            JSONObject data = jsonObject.getJSONObject("data");
            GoFastDfsCfg goFastDfsCfg = JSONObject.parseObject(data.toJSONString(), GoFastDfsCfg.class);
            return ResponseBean.success("获取成功", goFastDfsCfg);
        }
        return ResponseBean.fail("本机未安装goFastDfs");
    }


    /**
     * @description 校验服务
     * @param peers peers
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    @RequestMapping("/install/checkServer")
    @ResponseBody
    @Validated
    public ResponseBean checkServer(@Valid Peers peers, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseBean.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        if (!Validator.isUrl(peers.getServerAddress())){
            return ResponseBean.fail("请正确填写集群服务地址!");
        }
        if (StringUtils.isNotBlank(peers.getShowAddress()) && !Validator.isUrl(peers.getShowAddress())){
            return ResponseBean.fail("请正确填写访问域名!");
        }
        try{
            String urlPath = peers.getServerAddress();
            if(StrUtil.isNotBlank(peers.getGroupName())){
                urlPath += "/" + peers.getGroupName();
            }
            String result = HttpRequest.get(urlPath + Constant.API_STAT).timeout(2000).execute().body();
            ApiResult apiResult = JSONUtil.toBean(result, ApiResult.class);
            if(!apiResult.getStatus().equals(Constant.API_STATUS_SUCCESS)) {
                return ResponseBean.fail("连接GoFastDfs服务失败!请检查服务地址是否正确,以及是否配置白名单!");
            }
            return ResponseBean.success("校验通过!");
        }catch(Exception e){
            return ResponseBean.fail("连接GoFastDfs服务失败!请检查服务地址是否正确,以及是否配置白名单!");
        }
    }

    /** 
     * @description 安装
     * @param installForm installForm
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */ 
    @RequestMapping("/install/doInstall")
    @ResponseBody
    @Validated
    public ResponseBean doInstall(@Valid InstallForm installForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseBean.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        Peers peers = installForm.getPeers();
        if (peersService.save(peers)) {
            User user = installForm.getUser();
            user.setPeersId(peers.getId());
            if (userService.save(user)) {
                return ResponseBean.success("安装成功");
            }
            return ResponseBean.fail("安装失败");
        }
        return ResponseBean.fail("安装失败");
    }
}
