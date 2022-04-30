package org.competition.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SystemFunction {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	public static boolean isCrashDate(Date date1,Date date2, Date date3, Date date4) {
		Date startDate = null;
		Date endDate = null;
		Date minDate = null;
		Date maxDate = null;
		if(date1.before(date2)){
			startDate =date1;
			endDate =date2;
		}else{
			startDate =date2;
			endDate =date1;
		}
		if(date3.before(date4)){
			minDate =date3;
			maxDate =date4;
		}else{
			minDate =date4;
			maxDate =date3;
		}
		boolean result = false;
		if((endDate.before(minDate)||endDate.equals(minDate))||(startDate.after(maxDate)||startDate.equals(maxDate))){}else{result = true;}
		return result;
	}

	/**
	 * 获取当前日期是周几<br>
	 * 
	 * @param date
	 * @return 当前日期是周几
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}
	
	public static String firstLetterToUpper(String str) {
		char[] array = str.toCharArray();
		array[0] -= 32;
		return String.valueOf(array);
	}

	public static String firstLetterToLower(String str) {
		char[] array = str.toCharArray();
		array[0] += 32;
		return String.valueOf(array);
	}

	public static void StringToFile(String path, String content)
			throws IOException {
		File file = new File(path);
		if (file.exists() && file.isFile())
			file.delete();
		file.createNewFile();
		StringBuffer sb = new StringBuffer();
		sb.append(content);
		FileOutputStream out = new FileOutputStream(file, true);
		out.write(sb.toString().getBytes("utf-8"));
		out.close();
	}
	public static void StringAddToFile(String path, String content)
			throws IOException {
		File file = new File(path);
		StringBuffer sb = new StringBuffer();
		if (file.exists() && file.isFile() && file.length()!=0)
			sb.append("\r\n");
		sb.append(content);
		FileOutputStream out = new FileOutputStream(file, true);
		out.write(sb.toString().getBytes("utf-8"));
		out.close();
	}
	public static String dateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	public static List<Date> getBetweenDates(Date start, Date end) {
		Date tempDate = null;
		if(end.before(start)){
			tempDate=start;
			start = end;
			end = tempDate;
		}
	    List<Date> result = new ArrayList<Date>();
	    result.add(start);
	    Calendar tempStart0 = Calendar.getInstance();
	    Calendar tempStart = Calendar.getInstance();
	    tempStart0.setTime(start);
	    tempStart.setTime(start);
	    tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    
	    Calendar tempEnd = Calendar.getInstance();
	    tempEnd.setTime(end);
	    
	    while ((tempStart.get(Calendar.DAY_OF_YEAR)<(tempEnd.get(Calendar.DAY_OF_YEAR))&&tempStart.get(Calendar.YEAR)==(tempEnd.get(Calendar.YEAR)))||tempStart.get(Calendar.YEAR)<(tempEnd.get(Calendar.YEAR))) {
	        result.add(tempStart.getTime());
	        tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    if((tempStart0.get(Calendar.DAY_OF_YEAR)<(tempEnd.get(Calendar.DAY_OF_YEAR))&&tempStart0.get(Calendar.YEAR)==(tempEnd.get(Calendar.YEAR)))||tempStart0.get(Calendar.YEAR)<(tempEnd.get(Calendar.YEAR)))result.add(end);
	    return result;
	}
	public static boolean isExsit(String v,List<String> list) {
		for(String s:list) {
			if(!s.equals("")&&!v.equals("")&&s.equals(v))return true;
		}
		return false;
	}
}
