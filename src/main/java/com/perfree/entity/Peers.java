package com.perfree.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 集群实体类
 */
@Data
public class Peers implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotBlank(message = "集群名称不能为空且在100字符以内")
    @Size(max = 100)
    private String name;
    @Size(max =30)
    private String groupName;
    @NotBlank(message = "集群管理地址不能为空且在100字符以内")
    @Size(max = 100)
    private String serverAddress;
    private String createTime;
}
