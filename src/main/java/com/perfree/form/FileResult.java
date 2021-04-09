package com.perfree.form;

public class FileResult {
    private Boolean is_dir;
    private String md5;
    private String mTime;
    private String name;
    private String path;
    private String size;
    private String peerAddr;

    public Boolean getIs_dir() {
        return is_dir;
    }

    public void setIs_dir(Boolean is_dir) {
        this.is_dir = is_dir;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPeerAddr() {
        return peerAddr;
    }

    public void setPeerAddr(String peerAddr) {
        this.peerAddr = peerAddr;
    }
}
