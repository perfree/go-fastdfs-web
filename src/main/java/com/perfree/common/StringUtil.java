package com.perfree.common;

import java.util.UUID;

/**
 * String类型操作工具类
 * @author Perfree
 *
 */
public class StringUtil {

	/**
	 * 获取UUID
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", ""); 
	}
	
	/**
	 * 判断字符串是否为空值
	 * @param str
	 * @return Boolean
	 */
	public static Boolean isBlank(String str) {
		if(str == null || str == "") {
			return true;
		}
		return false;
	}
}
