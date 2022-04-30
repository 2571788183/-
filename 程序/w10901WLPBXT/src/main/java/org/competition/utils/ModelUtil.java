package org.competition.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.competition.model.PersistenceModel;

public class ModelUtil {
	public static Map<String,PersistenceModel> values(Class clazz,Object object) throws Exception {
		Map<String,PersistenceModel> map = new LinkedHashMap<String, PersistenceModel>();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field: fields){
			boolean annotationFlag = true;
			String fieldName = field.getName();
			String upperChar = fieldName.substring(0,1).toUpperCase();
			String anotherStr = fieldName.substring(1);
			String methodName = "get" + upperChar + anotherStr;
			Method method = clazz.getMethod(methodName);
			method.setAccessible(true);
			for(Annotation annotation:method.getDeclaredAnnotations()) {
				if(annotation.annotationType().toString().contains("OneToMany")
					||annotation.annotationType().toString().contains("Transient")
						)annotationFlag=false;
			}
			if(annotationFlag) {
				Object resultValue = method.invoke(object, new Object[]{});
				Class fieldClass = field.getType();
				PersistenceModel pm = new PersistenceModel();
				pm.setValue(resultValue);
				if(fieldClass == int.class) {
					pm.setJdbcType("INTEGER");
				}else if(fieldClass == Integer.class) {
					pm.setJdbcType("INTEGER");
				}else if(fieldClass == String.class) {
					pm.setJdbcType("VARCHAR");
				}else if(fieldClass == Date.class) {
					pm.setJdbcType("TIMESTAMP");
				}else {
					Method getIdMethod = fieldClass.getMethod("getId", new Class[]{});
					getIdMethod.setAccessible(true);
					Object resultValue2 = getIdMethod.invoke(resultValue, new Object[]{});
					pm.setValue(resultValue2);
					pm.setJdbcType("INTEGER");
				}
				map.put(fieldName,pm);
			}
		}
		return map;
	}
}
