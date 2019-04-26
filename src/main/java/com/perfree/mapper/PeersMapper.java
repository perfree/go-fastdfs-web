package com.perfree.mapper;

import com.perfree.entity.Peers;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    @Insert("INSERT INTO t_peers(name,groupName, serverAddress, createTime) VALUES(#{peers.name},#{peers.groupName}, #{peers.serverAddress}, #{peers.createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "peers.id")
    int add(@Param("peers") Peers peers);

    /**
     * 根据用户账户获取集群信息
     * @param id
     * @return Peers
     */
    @Select("SELECT * FROM t_peers WHERE id=#{id}")
    Peers getPeersById(Integer id);

    /**
     * 获取所有集群信息
     * @return List<Peers>
     */
    @Select("SELECT * FROM t_peers ORDER BY id DESC")
    List<Peers> getAllPeers();

    /**
     * 根据集群地址查询数量
     * @param serverAddress
     * @return long
     */
    @Select("SELECT count(1) FROM t_peers WHERE serverAddress = #{serverAddress}")
    long checkPeers(String serverAddress);

    /**
     * 根据id删除集群
     * @param id
     * @return int
     */
    @Delete("DELETE FROM t_peers WHERE id = #{id}")
    int delPeersById(int id);

    /**
     * 更新集群
     * @param peers
     * @return int
     */
    @Update("UPDATE t_peers SET name=#{name},groupName=#{groupName},serverAddress=#{serverAddress} WHERE id=#{id}")
    int updatePeers(Peers peers);
}
