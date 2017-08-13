package com.hundsun.hscar.constant;

public enum UserTypeEnum {
	PASSENGER(1),DRIVER(2);
	
	private final Integer value;

	private UserTypeEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	
}
