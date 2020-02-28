package com.msz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串工具类
 * @author oyl
 *
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean IsNotEmpty(String str){
		if(str == null){
			return false;
		}
		return str.length() > 0 ? true : false;
	}
	
	/**
	 * 字符串转换为日期
	 * @param str
	 * @return
	 */
	public static Date StringToDate(String str){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date d = null;
		
		try {
			d = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 把日期类型转换为字符串格式
	 * @param date
	 * @return
	 */
	public static String DateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String str = sdf.format(date);
		
		return str;
	}
	
	
	
}
