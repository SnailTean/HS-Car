package com.hundsun.hscar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author Ryan
 * @email ryansunboy@gmail.com
 * @date 2017-08-09
 */
public class OrderUserRelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer relId; // 
	
	private Integer orderId; // 
	
	private Integer passengerId; // 
	
	private Integer driverId; // 



	/**
	 * 设置：
	 */
	public void setRelId(Integer relId) {
		this.relId = relId;
	}
	/**
	 * 获取：
	 */
	public Integer getRelId() {
		return relId;
	}

	/**
	 * 设置：
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 设置：
	 */
	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}
	/**
	 * 获取：
	 */
	public Integer getPassengerId() {
		return passengerId;
	}

	/**
	 * 设置：
	 */
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	/**
	 * 获取：
	 */
	public Integer getDriverId() {
		return driverId;
	}
}
