package com.hundsun.hscar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 司机信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
public class DriverEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long driverId; // 
	
	private Long userId; // 用户ID
	
	private String driverLicenseNumber; // 驾照号码
	
	private String plateNumber; // 车牌号
	
	private Date createTime; // 创建时间
	
	private Date updateTime; // 更新时间



	/**
	 * 设置：
	 */
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	/**
	 * 获取：
	 */
	public Long getDriverId() {
		return driverId;
	}

	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：驾照号码
	 */
	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}
	/**
	 * 获取：驾照号码
	 */
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	/**
	 * 设置：车牌号
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	/**
	 * 获取：车牌号
	 */
	public String getPlateNumber() {
		return plateNumber;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
