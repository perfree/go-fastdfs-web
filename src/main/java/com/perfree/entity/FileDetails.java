package com.perfree.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author Perfree
 * @Date 2019/5/11 11:13
 */
@Data
public class FileDetails {
    private String path;
    private String size;
    private String name;
    private String md5;
    private String scene;
    private String timeStamp;
    private List<String> peers;
}
