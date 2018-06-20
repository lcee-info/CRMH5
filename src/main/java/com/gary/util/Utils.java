package com.gary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
  * @ClassName: Utils
  * @Description: 工具类
  * @author Comsys-Gary Hu
  * @date 2017年4月13日 下午3:13:41
  *
 */
public class Utils {
	/**
	 * 将 Date 类型转换成 Calendar
	 * @param date 日期
	 * @return Calendar ca
	 * @throws ParseException 
	 */
	public static Calendar changeDataForCalendar(String dateStr) throws ParseException{
		Date date = null;
		Calendar calendar = null;
		if(dateStr != null)
		{
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date =sdf.parse(dateStr);
			calendar = Calendar.getInstance();
			calendar.setTime(date);
		}
		return calendar;
	}
}
