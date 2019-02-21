package com.yonyou.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.sf.json.JSONObject;


/**
 * 集团，省市，部门限额查询，取页面选择的月份，自动加上日期，天数
 * 
 * @author zengq
 * 
 */
public class DateUtil {
	
	
	// 1.使用calendar类实现
	/**
	 * 取得当月天数
	 * */
	public static int getCurrentMonthLastDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	
	/**
	 * 得到指定月的天数
	 * */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.getNowTimestamp());
		String str ="{\"retCode\":\"000000\",\"retMsg\":\"正常\",\"data\":{\"ID\":\"\",\"NAME\":\"zzh\",\"LOCK_STATUS\":\"0\",\"LOGIN_ID\":\"admin\",\"PASSWORD\":\"111111\",\"AUTHEN_TYPE\":\"0\",\"EMAIL\":\"zzh@163.com\",\"LOGIN_STATUS\":\"0\"}}";
		JSONObject.fromObject(str);
		
		
//		System.out.println(String.format("%9d", 1).replace(" ", "0"));
		
	}

	public static String parseDateString(String date_pattern, Date date) {
		if (date_pattern.equals("")) {
			date_pattern = "yyyy-MM-dd hh:mm:ss";
		}
		if (date.equals("")) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(date_pattern);
		return sdf.format(date).toString();
	}
	
	public static String getNowDate() {
		Date date = new Date();
		String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return nowDate;
	}
	
	public static String getNowDateYYMMDD() {
		Date date = new Date();
		String nowDate = new SimpleDateFormat("yyyyMMdd").format(date);
		return nowDate.substring(2);
	}
	
	public static String getNowDateYYYYMMDD() {
		Date date = new Date();
		String nowDate = new SimpleDateFormat("yyyyMMdd").format(date);
		return nowDate;
	}
	
	public static String getNowDateYYMMDDHH() {
		Date date = new Date();
		String nowDate = new SimpleDateFormat("yyyyMMddhh").format(date);
		return nowDate.substring(2);
	}
	
	public static String getNowTimestamp() {
		Date date = new Date();
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(date);
		return nowDate;
	}
	
	
	public static String getNowDateAfterYYMMDD(int num) {
		 Date date=new Date();//取时间 
	     Calendar   calendar   =   new   GregorianCalendar(); 
	     calendar.setTime(date); 
	     calendar.add(calendar.DATE,num);//把日期往后增加一天.整数往后推,负数往前移动 
	     date=calendar.getTime(); 
		return new SimpleDateFormat("yyyyMMdd").format(date).substring(2);
	}
	public static String getNowDateBeforeYYMMDD(int num) {
		 Date date=new Date();//取时间 
	     Calendar   calendar   =   new   GregorianCalendar(); 
	     calendar.setTime(date); 
	     calendar.add(calendar.DATE,-num);//把日期往后减少一天.整数往后推,负数往前移动 
	     date=calendar.getTime(); 
		return new SimpleDateFormat("yyyyMMdd").format(date).substring(2);
	}
	
}

// 2.使用自己编写的函数实现
//
// view plaincopy to clipboardprint?
/**
 * 日期工具类 by hpf
 * */
class MyDateUtils {
	// 返回当前年月日
	public String getNowDate() {
		Date date = new Date();
		String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(date);
		return nowDate;
	}

	// 返回当前年份
	public int getYear() {
		Date date = new Date();
		String year = new SimpleDateFormat("yyyy").format(date);
		return Integer.parseInt(year);
	}

	public String getDateStringByDate(String pattern, Date date)
			throws Exception {
		String date_str = "";
		Integer year = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(date));
		Integer month = Integer.parseInt(new SimpleDateFormat("MM")
				.format(date));
		Integer days = this.getDays(year, month);
		date_str = year + "-" + month + "-" + days;
		return date_str;
	}
	
	
	

	// 返回当前月份
	public int getMonth() {
		Date date = new Date();
		String month = new SimpleDateFormat("MM").format(date);
		return Integer.parseInt(month);
	}

	// 判断闰年
	public boolean isLeap(int year) {
		if (((year % 100 == 0) && year % 400 == 0)
				|| ((year % 100 != 0) && year % 4 == 0))
			return true;
		else
			return false;
	}

	// 返回当月天数
	public int getDays(int year, int month) {
		int days;
		int FebDay = 28;
		if (isLeap(year))
			FebDay = 29;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			days = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		case 2:
			days = FebDay;
			break;
		default:
			days = 0;
			break;
		}
		return days;
	}

	// 返回当月星期天数
	public int getSundays(int year, int month) {
		int sundays = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Calendar setDate = Calendar.getInstance();
		// 从第一天开始
		int day;
		for (day = 1; day <= getDays(year, month); day++) {
			setDate.set(Calendar.DATE, day);
			String str = sdf.format(setDate.getTime());
			if (str.equals("星期日")) {
				sundays++;
			}
		}
		return sundays;
	}

	public static void main(String[] args) {
		MyDateUtils du = new MyDateUtils();
		System.out.println("今天日期是：" + du.getNowDate());
		System.out.println("本月有" + du.getDays(du.getYear(), du.getMonth())
				+ "天");
		System.out.println("本月有" + du.getSundays(du.getYear(), du.getMonth())
				+ "个星期天");
		try {
			Date d = new Date();
			d.setYear(2011);
			d.setMonth(1);
			System.out.println(du.getDateStringByDate("", new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
