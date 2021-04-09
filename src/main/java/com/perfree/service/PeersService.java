package com.perfree.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.perfree.common.PageResult;
import com.perfree.mapper.PeersMapper;
import com.perfree.model.Peers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Perfree
 * @description Peers集群表, 逻辑处理
 * @date 2021/3/22 15:00
 */
@Service
public class PeersService extends ServiceImpl<PeersMapper, Peers> {
    @Autowired
    private PeersMapper peersMapper;

    public PageResult<Peers> listPage(int page, int limit) {
        PageResult<Peers> result = new PageResult<>();
        PageHelper.startPage(page, limit);
        List<Peers> allPeers = list();
        PageInfo<Peers> pageInfo = new PageInfo<>(allPeers);
        result.setTotal(pageInfo.getTotal());
        result.setState(200);
        result.setData(pageInfo.getList());
        return result;
    }

    /**
     * 检查集群是否已存在
     *
     * @param serverAddress serverAddress
     * @return boolean
     */
    public boolean checkPeers(String serverAddress) {
        QueryWrapper<Peers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("serverAddress", serverAddress);
        return peersMapper.selectCount(queryWrapper) > 0;
    }

    /**
     * 新增集群
     *
     * @param peers peers
     * @return boolean
     */
    public boolean addPeers(Peers peers) {
        peers.setCreateTime(new Date());
        return peersMapper.insert(peers) > 0;
    }

    /**
     * 根据id删除集群
     *
     * @param id id
     * @return boolean
     */
    public boolean delPeersById(int id) {
        return peersMapper.deleteById(id) > 0;
    }
}
