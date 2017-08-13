package org.agile.common.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public  class CommonUtils {
	
	/**
	 * 对实体，字符串，集合，map进行空判断
	 * @param object
	 * @return
	 */
	public static  boolean  isEmpty(Object object){
		if(object == null){
			return true;
		}else if(object instanceof String){
			return ((String) object).isEmpty();
			 
		}else if(object instanceof Collection){
			return ((Collection<?>) object).isEmpty();
		}else if(object instanceof Map){
			return ((Map<?, ?>) object).isEmpty();
		}else {
			return isEmptyBean(object);
		}
	}
	private static boolean isEmptyBean(Object object) {
		
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if(field.get(object)!=null){
					return false;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return false;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		return true;
	}
	public static boolean isNotEmpty(Object object){
		return !isEmpty(object);
	}

}
