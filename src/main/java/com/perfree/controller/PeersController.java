package com.perfree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 集群管理Controller
 */
@Controller
public class PeersController {

    /**
     * 集群列表
     * @return
     */
    @RequestMapping("/peers")
    public String index(){
        return "peers/list";
    }
}
