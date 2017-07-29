package com.hundsun.hscar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 路线详情表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
public class RouteDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long routeId; // 
	
	private String departure; // 出发地
	
	private String destination; // 目的地
	
	private String depCoordinate; // 目的地坐标
	
	private String desCoordinate; // 出发地坐标
	
	private Long userId; // 用户ID
	
	private Date createTime; // 创建时间
	
	private Date updateTime; // 更新时间



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
	 * 设置：目的地坐标
	 */
	public void setDepCoordinate(String depCoordinate) {
		this.depCoordinate = depCoordinate;
	}
	/**
	 * 获取：目的地坐标
	 */
	public String getDepCoordinate() {
		return depCoordinate;
	}

	/**
	 * 设置：出发地坐标
	 */
	public void setDesCoordinate(String desCoordinate) {
		this.desCoordinate = desCoordinate;
	}
	/**
	 * 获取：出发地坐标
	 */
	public String getDesCoordinate() {
		return desCoordinate;
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
}
