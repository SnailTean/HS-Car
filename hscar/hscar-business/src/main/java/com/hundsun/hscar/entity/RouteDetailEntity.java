package com.hundsun.hscar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 路线详情表
 * 
 * @author Ryan
 * @email ryansunboy@gmail.com
 * @date 2017-08-13
 */
public class RouteDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long routeId; // 
	
	private String departure; // 出发地
	
	private String destination; // 目的地
	
	private Double depLongitude; // 出发地经度
	
	private Double depLatitude; // 出发地纬度
	
	private Double desLongitude; // 目的地经度
	
	private Double desLatitude; // 目的地纬度
	
	private Long userId; // 用户ID
	
	private Date createTime; // 创建时间
	
	private Date updateTime; // 更新时间
	
	private Integer routeStatus; // 0:保存 1:生效 2:失效
	
	private Integer userType; // 1:乘客 2司机



	/**
	 * 设置：
	 */
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}
	/**
	 * 获取：
	 */
	public Long getRouteId() {
		return routeId;
	}

	/**
	 * 设置：出发地
	 */
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	/**
	 * 获取：出发地
	 */
	public String getDeparture() {
		return departure;
	}

	/**
	 * 设置：目的地
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * 获取：目的地
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * 设置：出发地经度
	 */
	public void setDepLongitude(Double depLongitude) {
		this.depLongitude = depLongitude;
	}
	/**
	 * 获取：出发地经度
	 */
	public Double getDepLongitude() {
		return depLongitude;
	}

	/**
	 * 设置：出发地纬度
	 */
	public void setDepLatitude(Double depLatitude) {
		this.depLatitude = depLatitude;
	}
	/**
	 * 获取：出发地纬度
	 */
	public Double getDepLatitude() {
		return depLatitude;
	}

	/**
	 * 设置：目的地经度
	 */
	public void setDesLongitude(Double desLongitude) {
		this.desLongitude = desLongitude;
	}
	/**
	 * 获取：目的地经度
	 */
	public Double getDesLongitude() {
		return desLongitude;
	}

	/**
	 * 设置：目的地纬度
	 */
	public void setDesLatitude(Double desLatitude) {
		this.desLatitude = desLatitude;
	}
	/**
	 * 获取：目的地纬度
	 */
	public Double getDesLatitude() {
		return desLatitude;
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

	/**
	 * 设置：0:保存 1:生效 2:失效
	 */
	public void setRouteStatus(Integer routeStatus) {
		this.routeStatus = routeStatus;
	}
	/**
	 * 获取：0:保存 1:生效 2:失效
	 */
	public Integer getRouteStatus() {
		return routeStatus;
	}

	/**
	 * 设置：1:乘客 2司机
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	/**
	 * 获取：1:乘客 2司机
	 */
	public Integer getUserType() {
		return userType;
	}
}
