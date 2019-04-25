package com.perfree.controller;

import com.perfree.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统设置Controller
 */
@Controller
public class SettingController extends BaseController{

    @Autowired
    private SettingService settingService;

    /**
     * 基础设置
     * @return String
     */
    @RequestMapping("/settings/setting")
    public String setting(){
        return "settings/setting";
    }

    /**
     * 个人资料
     * @return String
     */
    @RequestMapping("/settings/user")
    public String user(Model model){
        model.addAttribute("user",getUser());
        return "settings/user";
    }
}
