package com.perfree.common;

import lombok.Data;

@Data
public class AjaxResult {

	/**ajax响应码500出错*/
	public static final int AJAX_ERROR = 500;
	/**ajax响应码200成功*/
	public static final int AJAX_SUCCESS = 200;
	
	/**
	 * Ajax结果集
	 * @param state 状态
	 * @param msg 信息
	 * @param data 数据
	 */
	public AjaxResult(Integer state, String msg, Object data) {
		this.state = state;
		this.msg = msg;
		this.data = data;
	}
	
	/**
	 * Ajax结果集
	 * @param state 状态
	 * @param msg 信息
	 */
	public AjaxResult(Integer state, String msg) {
		this.state = state;
		this.msg = msg;
	}
	
	/**
	 * Ajax结果集
	 * @param state 状态
	 */
	public AjaxResult(Integer state) {
		this.state = state;
	}
	
	/**
	 * Ajax结果集
	 * @param state 状态
	 * @param data 数据
	 */
	public AjaxResult(Integer state, Object data) {
		this.state = state;
		this.data = data;
	}
	
	private Integer state;
	private String msg;
	private Object data;
}
