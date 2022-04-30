package org.competition.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class DateConverter implements Converter<String, Date>{
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public Date convert(String value) {
		try {
		return format.parse(value);
		} catch (ParseException e) {
			try {
				return format2.parse(value);
			} catch (ParseException e2) {
			}
		}
		return new Date();
	}
}
