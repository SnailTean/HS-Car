package com.hundsun.hscar.constant;

public enum OrderTypeEnum {

	REALTIME(1),APPOINTMENT(2);
	
	private final Integer value;

	private OrderTypeEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	
	
	
}
