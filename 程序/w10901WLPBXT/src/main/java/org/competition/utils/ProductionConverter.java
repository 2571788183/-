package org.competition.utils;
import org.competition.model.Production;import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class ProductionConverter implements Converter<String, Production>{
	@Override
	public Production convert(String value) {
		Production production =null;
		production = new Production();
		production.setId(Integer.parseInt(value));
		return production;
	}
}
