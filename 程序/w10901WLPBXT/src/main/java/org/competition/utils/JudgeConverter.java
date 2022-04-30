package org.competition.utils;
import org.competition.model.Judge;import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class JudgeConverter implements Converter<String, Judge>{
	@Override
	public Judge convert(String value) {
		Judge judge =null;
		judge = new Judge();
		judge.setId(Integer.parseInt(value));
		return judge;
	}
}
