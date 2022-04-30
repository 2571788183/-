package org.competition.utils;
import org.apache.commons.beanutils.Converter;
import org.competition.model.Judge;

public class JudgeConvert implements Converter {

	public Object convert(Class targetClass, Object value) {
		Judge judge =null;
		if (targetClass == Judge.class){
			 judge = new Judge();
			 judge.setId(Integer.parseInt((String)value));
		}
		return judge;
	}
}

