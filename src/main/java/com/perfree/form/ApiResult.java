package com.perfree.form;

import cn.hutool.json.JSONObject;

import java.io.Serializable;

/**
 * @description GoFastDfs API接口响应格式
 * @author Perfree
 * @date 2021/3/23 10:55
 */
public class ApiResult implements Serializable {
    private static final long serialVersionUID = -2561810964453277221L;
    private String status;
    private String message;
    private JSONObject data;

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
