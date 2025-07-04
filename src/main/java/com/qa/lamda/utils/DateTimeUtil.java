package com.qa.lamda.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateTimeUtil {
	
	public static SimpleDateFormat formatDate(String dateFormat) {
		//eg : "MM/dd/yyyy hh:mm", "yyyy-MM-dd hh:mm"
		return new SimpleDateFormat(dateFormat);
	}
	
	public static String getFormatDateTime(Date date,String dateFormat) {
		SimpleDateFormat sdf = formatDate(dateFormat);
		return sdf.format(date);
	}
	
	public static String getFormatCurrentDateTime(String dateFormat) {
		SimpleDateFormat sdf = formatDate(dateFormat);
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}
	
	public static String getFormatDateTime(String date,String dateFormat) {
		SimpleDateFormat sdf = formatDate(dateFormat);
		
		Date dt = getDateFromString(date);
		return sdf.format(dt);
	}
	
	//if previous pass -1,-2 and future pass 1,2 etc
	public static String getFutureOrPreviousDateTime(int previousDaysBy) {
		SimpleDateFormat df = formatDate("MM/dd/yyyy hh:mm");
		Calendar calobj = Calendar.getInstance();
		calobj.add(Calendar.DATE, previousDaysBy);
		
		String dateOne = df.format(calobj.getTime());
		
		return dateOne;
		
	}
	
	public static String getFutureOrPreviousDate(int previousDaysBy) {
		SimpleDateFormat df = formatDate("MM/dd/yyyy");
		Calendar calobj = Calendar.getInstance();
		calobj.add(Calendar.DATE, previousDaysBy);
		
		String dateOne = df.format(calobj.getTime());
		
		return dateOne;
		
	}
	
	public static String getCurrentDate() {
		SimpleDateFormat df = formatDate("MM/dd/yyyy");
		Calendar calobj = Calendar.getInstance();
//		calobj.add(Calendar.DATE, previousDaysBy);
		
		String dateOne = df.format(calobj.getTime());
		
		return dateOne;
	}
	
	public static Date getFormatedDateFromString(String date, String format) {
		Date dt = null;
		
		try {
			SimpleDateFormat sdf = formatDate(format);
			dt = sdf.parse(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return dt;
		
	}
	
	public static String getStringFromDate(Date date) {
		SimpleDateFormat sdf = formatDate("MM/dd/yyyy");
		return sdf.format(date);
	}
	
	public static int getRandomNoWithInRange(int range) {
		Random random = new Random();
		return random.nextInt(range);
	}

	private static Date getDateFromString(String date) {
		Date dt = null;
		
		try {
			SimpleDateFormat sdf = formatDate(date);
			dt = sdf.parse(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return dt;
	}
	
	

}
