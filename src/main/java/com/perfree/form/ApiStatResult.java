package com.perfree.form;

import java.io.Serializable;

/**
 * @description GoFastDfs stat API接口响应格式
 * @author Perfree
 * @date 2021/3/23 10:55
 */
public class ApiStatResult implements Serializable {
    private static final long serialVersionUID = -2561810964452277221L;

    private String date;
    private String totalSize;
    private Long fileCount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public Long getFileCount() {
        return fileCount;
    }

    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
    }
}
