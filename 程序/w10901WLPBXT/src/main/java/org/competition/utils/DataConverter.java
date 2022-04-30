package org.competition.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DataConverter implements Converter<String, Date> {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date convert(String s) {
        if(!s.isEmpty()){
            try {
                return dateFormat.parse(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
