package com.hundsun.hscar.constant;

public enum RouteStatusEnum {
	
	SAVE(0),ACTIVED(1),EXPIRED(2);
	
	private final Integer value;

	private RouteStatusEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	

}
