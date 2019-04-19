package com.perfree.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.perfree.common.AjaxResult;
import com.perfree.common.DateUtil;
import com.perfree.common.PageResult;
import com.perfree.entity.Peers;
import com.perfree.mapper.PeersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 集群service
 */
@Service
public class PeersService {

    @Autowired
    private PeersMapper peersMapper;

    /**
     * 集群列表分页
     * @param page
     * @param limit
     * @return
     */
    public PageResult<Peers> page(int page, int limit) {
        PageResult<Peers> result = new PageResult<>();
        PageHelper.startPage(page,limit);
        List<Peers> allPeers = peersMapper.getAllPeers();
        PageInfo<Peers> pageInfo = new PageInfo<>(allPeers);
        result.setTotal(pageInfo.getTotal());
        result.setState(200);
        result.setData(pageInfo.getList());
        return result;
    }

    /**
     * 新增集群
     * @param peers
     * @return boolean
     */
    public boolean addPeers(Peers peers) {
        peers.setCreateTime(DateUtil.getFormatDate(new Date()));
        if(peersMapper.add(peers) > 0){
            return true;
        }
        return false;
    }


    /**
     * 检查集群是否已存在
     * @param serverAddress
     * @return boolean
     */
    public boolean checkPeers(String serverAddress) {
        if(peersMapper.checkPeers(serverAddress) > 0){
            return true;
        }
        return false;
    }

    /**
     * 根据id删除集群
     * @param id
     * @return boolean
     */
    public boolean delPeersById(int id) {
        if(peersMapper.delPeersById(id) > 0){
            return true;
        }
        return false;
    }

    /**
     * 根据id获取集群信息
     * @param id
     * @return Peers
     */
    public Peers getPeersById(int id) {
        return peersMapper.getPeersById(id);
    }

    /**
     * 更新集群信息
     * @param peers
     * @return boolean
     */
    public boolean updatePeers(Peers peers) {
        if(peersMapper.updatePeers(peers) > 0){
            return true;
        }
        return false;
    }
}
