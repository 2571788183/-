package org.competition.utils;
import org.competition.model.Shenhe;import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class ShenheConverter implements Converter<String, Shenhe>{
	@Override
	public Shenhe convert(String value) {
		Shenhe shenhe =null;
		shenhe = new Shenhe();
		shenhe.setId(Integer.parseInt(value));
		return shenhe;
	}
}
