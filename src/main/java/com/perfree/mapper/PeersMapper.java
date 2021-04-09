package com.perfree.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.perfree.model.Peers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @description Peers集群表,Mapper接口
 * @author Perfree
 * @date 2021/3/22 14:58
 */
@Mapper
@Component
public interface PeersMapper extends BaseMapper<Peers> {
}
