package org.competition.utils;
import org.apache.commons.beanutils.Converter;
import org.competition.model.Shenhe;

public class ShenheConvert implements Converter {

	public Object convert(Class targetClass, Object value) {
		Shenhe shenhe =null;
		if (targetClass == Shenhe.class){
			 shenhe = new Shenhe();
			 shenhe.setId(Integer.parseInt((String)value));
		}
		return shenhe;
	}
}

