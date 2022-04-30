package org.competition.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IntegerConverter implements Converter<String, Integer> {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Integer convert(String s) {
        if(!s.isEmpty()){
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
