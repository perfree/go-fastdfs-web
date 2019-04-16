package com.perfree.common;

import java.text.DecimalFormat;

/**
 * 文件大小转换工具类
 * @author Perfree
 *
 */
public class FileSizeUtil {

	/**
     * 根据文件大小转换为B、KB、MB、GB单位字符串显示
     * @param filesize 文件的大小（long型）
     * @return 返回 转换后带有单位的字符串
     */
    public static String GetLength(long filesize){
        String strFileSize = null;
        if(filesize < 1024){
            strFileSize = filesize+"B";
            return strFileSize;
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        if ((filesize >= 1024) && (filesize < 1024*1024)){
            strFileSize = df.format(((double)filesize)/1024)+"KB";
        }else if((filesize >= 1024*1024)&&(filesize < 1024*1024*1024)){
            strFileSize = df.format(((double)filesize)/(1024*1024))+"MB";
        }else{
            strFileSize = df.format(((double)filesize)/(1024*1024*1024))+"GB";
        }
        return strFileSize;
    }

    /**
     * @param filesize 文件的大小（long型）
     * @return 返回 转换后不带单位的字符串
     */
    public static String GetMBLength(long filesize){
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(((double)filesize)/(1024*1024));
    }
}
