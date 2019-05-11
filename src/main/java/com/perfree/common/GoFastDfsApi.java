package com.perfree.common;

/**
 * Go-FasTdFS API管理类
 * @author Perfree
 *
 */
public class GoFastDfsApi {

	/** 系统信息api */
	public static final String STATUS = "/status";
	/** 统计信息api */
	public static final String STAT = "/stat";
	/** 上传文件api */
	public static final String UPLOAD = "/upload";
	/** 删除文件api */
	public static final String DELETE = "/delete";
	/** 修复统计信息api */
	public static final String REPAIR_STAT = "/repair_stat";
	/** 删除空目录api */
	public static final String REMOVE_EMPTY_DIR = "/remove_empty_dir";
	/** 备份元数据api */
	public static final String BACKUP = "/backup";
	/** 同步失败修复api */
	public static final String REPAIR = "/repair";
	/** 文件列表api */
	public static final String LIST_DIR = "/list_dir";
	/** 文件信息api */
	public static final String GET_FILE_INFO = "/get_file_info";
}
