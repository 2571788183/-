package org.competition.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConvert implements Converter {

	public Object convert(Class targetClass, Object value) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (targetClass == Date.class) {
			date = new Date();
			try {
				date = format.parse((String) value);
			} catch (ParseException e) {
//				e.printStackTrace();
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
				if (targetClass == Date.class) {
					date = new Date();
					try {
						date = format2.parse((String) value);
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
//						e2.printStackTrace();
					}
				}
			}
		}
		return date;
	}
}
