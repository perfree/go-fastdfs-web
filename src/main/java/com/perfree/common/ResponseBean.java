package com.perfree.common;

/**
 * @description 接口响应
 * @author Perfree
 * @date 2021/3/23 10:33
 */
public class ResponseBean {
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;

    private int code;

    private String msg;

    private Object data;

    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @description 成功
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    public static ResponseBean success() {
        return new ResponseBean(SUCCESS_CODE, "success", "");
    }

    /**
     * @description 成功,附加数据
     * @param data data
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    public static ResponseBean success(Object data) {
        return new ResponseBean(SUCCESS_CODE, "success", data);
    }

    /**
     * @description  成功,附加信息及数据
     * @param msg msg
     * @param data data
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    public static ResponseBean success(String msg, Object data) {
        return new ResponseBean(SUCCESS_CODE, msg, data);
    }

    /**
     * @description 失败,附加信息及数据
     * @param msg msg
     * @param data data
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    public static ResponseBean fail(String msg, Object data) {
        return new ResponseBean(ERROR_CODE, msg, data);
    }

    /**
     * @description  失败,附加信息
     * @param msg  msg
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    public static ResponseBean fail(String msg) {
        return new ResponseBean(ERROR_CODE, msg,"");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
