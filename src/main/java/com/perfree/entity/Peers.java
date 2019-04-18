package com.perfree.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 集群实体类
 */
@Data
public class Peers implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String serverAddress;
    private String createTime;
}
