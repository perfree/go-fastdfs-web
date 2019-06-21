package com.perfree.entity;

import lombok.Data;

@Data
public class FileResult {
    private Boolean is_dir;
    private String md5;
    private String mTime;
    private String name;
    private String path;
    private String size;
    private String peerAddr;
}
