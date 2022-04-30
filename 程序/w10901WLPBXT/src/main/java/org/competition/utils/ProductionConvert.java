package org.competition.utils;
import org.apache.commons.beanutils.Converter;
import org.competition.model.Production;

public class ProductionConvert implements Converter {

	public Object convert(Class targetClass, Object value) {
		Production production =null;
		if (targetClass == Production.class){
			 production = new Production();
			 production.setId(Integer.parseInt((String)value));
		}
		return production;
	}
}

