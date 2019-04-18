package com.perfree.mapper;

import com.perfree.entity.Peers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * PeersMapper接口,因数据表不多,故采用注解方式开发
 * @author Perfree
 *
 */
public interface PeersMapper {

    /**
     * 新增集群地址,并返回主键
     * @param peers
     */
    @Insert("INSERT INTO t_peers(name, serverAddress, createTime) VALUES(#{peers.name}, #{peers.serverAddress}, #{peers.createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "peers.id")
    void add(@Param("peers") Peers peers);

    /**
     * 根据用户账户获取集群信息
     * @param id
     * @return Peers
     */
    @Select("SELECT * FROM t_peers WHERE id=#{id}")
    Peers getPeersById(Integer id);
}
