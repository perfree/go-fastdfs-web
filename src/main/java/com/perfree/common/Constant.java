package com.perfree.common;

/**
 * @description 常量
 * @author Perfree
 * @date 2021/3/23 10:58
 */
public class Constant {
    /** API 响应成功 */
    public static final String API_STATUS_SUCCESS = "ok";

    /** 配置管理API */
    public static final String API_RELOAD = "/reload";

    /** 文件统计信息API */
    public static final String API_STAT = "/stat";

    /** 文件上传API */
    public static final String API_UPLOAD = "/upload";

    /** 文件删除API */
    public static final String API_DELETE = "/delete";

    /** 文件信息API */
    public static final String API_GET_FILE_INFO = "/get_file_info";

    /** 文件列表API */
    public static final String API_LIST_DIR = "/list_dir";

    /** 修复统计信息API */
    public static final String API_REPAIR_STAT = "/repair_stat";
    public static final String API_REMOVE_EMPTY_DIR = "/remove_empty_dir";
    public static final String API_BACKUP = "/backup";

    /** 同步失败修复API */
    public static final String API_REPAIR = "/repair";

    /** 从文件目录中修复元数据API */
    public static final String API_REPAIR_FILE_INFO = "/repair_fileinfo";
    public static final String API_STATUS = "/status";
}
