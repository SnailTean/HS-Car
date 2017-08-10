package org.agile.common.utils;

import java.util.Collection;
import java.util.Map;

public  class CommonUtils {
	
	
	public static  boolean  isEmpty(Object object){
		if(object == null){
			return true;
		}
		if(object instanceof String){
			return ((String) object).isEmpty();
			 
		}
		if(object instanceof Collection){
			return ((Collection<?>) object).isEmpty();
		}
		
		if(object instanceof Map){
			return ((Map<?, ?>) object).isEmpty();
		}
		return false;
	}
	public static boolean isNotEmpty(Object object){
		return !isEmpty(object);
	}

}
