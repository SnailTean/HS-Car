package com.hundsun.hscar.vo;

import com.hundsun.hscar.entity.DriverEntity;
import com.hundsun.hscar.entity.UserEntity;

public class DriverUserVo {
	private UserEntity user;
	private DriverEntity driver;
	
	public UserEntity getUser() {
		return user;
	}
	
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	public DriverEntity getDriver() {
		return driver;
	}
	
	public void setDriver(DriverEntity driver) {
		this.driver = driver;
	}
	
}
