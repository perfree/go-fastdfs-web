package com.perfree.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @description peers集群表实体类
 * @author Perfree
 * @date 2021/3/22 14:35
 */
public class Peers implements Serializable {

    private static final long serialVersionUID = -554407295275500627L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank(message = "集群名称不能为空且在50字以内")
    @Size(max = 50, message = "集群名称不能为空且在50字以内")
    private String name;
    @Size(max =50, message = "组名称应在50字以内")
    private String groupName;
    @NotBlank(message = "集群服务地址不能为空且在100字以内")
    @Size(max = 100, message = "集群服务地址不能为空且在100字以内")
    private String serverAddress;
    @Size(max = 100, message = "访问域名应在50字以内")
    private String showAddress;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getShowAddress() {
        return showAddress;
    }

    public void setShowAddress(String showAddress) {
        this.showAddress = showAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
