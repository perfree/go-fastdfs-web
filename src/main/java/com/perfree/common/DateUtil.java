package com.perfree.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.perfree.common.StringUtil;

/**
 * 日期操作工具类
 * @author Perfree
 *
 */
public class DateUtil {
	/**
	 * 日期格式化
	 */
	public static String getFormatDate(String format){
		if(StringUtil.isBlank(format)){
			format="yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(format).format(new Date());
	}
	/**
	 * 日期格式化
	 */
	public static String getFormatDate(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	/**
	 * 日期格式化
	 */
	public static String getFormatDate(Date date,String format){
		if(StringUtil.isBlank(format)){
			format="yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(format).format(date);
	}
	/**
	* 字符串转换成日期
	* @param str 格式为yyyy-MM-dd HH:mm:ss   
	*/
	public static Date StrToDate(String str){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   return date;
	}
	/**
	* 字符串转换成日期
	* @param str 格式为yyyy-MM-dd HH:mm:ss   
	* @return date
	*/
	public static Date StrToDate(String str,String format){
		if(StringUtil.isBlank(format)){
			format="yyyy-MM-dd HH:mm:ss";
		}
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   return date;
	}
	/**
	 * 获得间隔后的格式化后的时间
	 * @param intervalTime 时间间隔 单位秒
	 * @param format 时间格式 （yyyy-MM-dd HH:mm:ss）
	 */
	public static String getIntervalFormatDate(long intervalTime,String format){
		if(StringUtil.isBlank(format)){
			format="yyyy-MM-dd HH:mm:ss";
		}
		long currentTime = System.currentTimeMillis() + intervalTime;
		Date date = new Date(currentTime);
		DateFormat df = new SimpleDateFormat(format);
		String nowTime=df.format(date);
		return nowTime;
	}

	/**
	 * 两个时间比较大小
	 * @param date1 入参1
	 * @param date2 入参2 
	 * @return 1：date1晚于date2，-1：date1早于date2，0：传入时间等于当前时间
	 */
	public static int compareDate(Date date1,Date date2){
		if (date1.getTime() > date2.getTime()) {
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            return -1;
        } else {//相等
            return 0;
        }
	}

	/**
	 * 两个时间比较大小
	 * @param date1 入参1
	 * @param date2 入参2 
	 * @return 1：date1晚于date2，-1：date1早于date2，0：传入时间等于当前时间
	 */
	public static int compareDate(String dateStr1,String dateStr2,String format){
		Date date1 = DateUtil.StrToDate(dateStr1,format);
		Date date2 = DateUtil.StrToDate(dateStr2,format);
		return compareDate(date1,date2);
	}
	
	/**
	 * 获取时间戳
	 * @return
	 */
	public static String getTimestamp() {
		return Long.toString(new Date().getTime() / 1000);
	}
	
	/**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @param containEndDate 是否包含结束日期，如果包含，则多加1
     * @return 相差天数
     * @throws ParseException
     */    
    public static int daysBetween(Date smdate,Date bdate,boolean containEndDate) throws ParseException{
    	try{
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    		return daysBetween(sdf.format(smdate),sdf.format(bdate),containEndDate);
    	}catch(Exception e){
    	}
    	return 0;
    }
      
	/**
	 * 字符串的日期格式的计算
	 */
    public static int daysBetween(String smdate,String bdate,boolean containEndDate){
    	try{
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    		return days(sdf.parse(smdate),sdf.parse(bdate),containEndDate);
    	}catch(Exception e){
    	}
    	return 0;
    }
    
    private static int days(Date smdate,Date bdate,boolean containEndDate) throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24)+(containEndDate?1:0);
		return Integer.parseInt(String.valueOf(between_days));
    }
    
	/**
	 * 日期向后或者向前推移
	 * @param dateStr 日期字符串，如："2019-11-11"
	 * @param intervalDay 日期推移值；如：1，传入日期参数向后推一天；-1，传入日期参数向前推一天
	 * @return 返回推移后的日期字符串
	 */
	public static String dayAddOrCut(String dateStr, int intervalDay){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try{
			date = sdf.parse(dateStr);//String 转 Date
		}catch(ParseException e){
			e.printStackTrace();
		}
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(Calendar.DATE, intervalDay);//把日期往后增加一天.整数往后推,负数往前移动 
		date = calendar.getTime();//这个时间就是日期往后推一天的结果
		return dateStr = sdf.format(date);
	}
}