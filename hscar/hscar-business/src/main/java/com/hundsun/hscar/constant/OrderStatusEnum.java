package com.hundsun.hscar.constant;

public enum OrderStatusEnum {
	PUBLISHING(0),PROCESSING(1),COMPLETE(2);
	
	private final Integer value;

	private OrderStatusEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	

}
