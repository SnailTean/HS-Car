package com.hundsun.hscar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 订单表
 * 
 * @author Ryan
 * @email ryansunboy@gmail.com
 * @date 2017-08-09
 */
public class CarpoolingOrdersEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long orderId; // 
	
	private Long routeId; // 
	
	private Integer orderType; // 1:即时订单、2:预约订单
	
	private Double price; // 价格
	
	private Double reward; // 奖励
	
	private Integer orderStatus; // 0.发布中1.处理中2.完成
	
	private Date createTime; // 创建时间
	
	private Date updateTime; // 更新时间
	
	private Date goTime; // 出发时间
	
	private Integer number; // 人数



	/**
	 * 设置：
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：
	 */
	public Long getOrderId() {
		return orderId;
	}

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
	 * 设置：1:即时订单、2:预约订单
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取：1:即时订单、2:预约订单
	 */
	public Integer getOrderType() {
		return orderType;
	}

	/**
	 * 设置：价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 设置：奖励
	 */
	public void setReward(Double reward) {
		this.reward = reward;
	}
	/**
	 * 获取：奖励
	 */
	public Double getReward() {
		return reward;
	}

	/**
	 * 设置：0.发布中
1.处理中
2.完成
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 获取：0.发布中
1.处理中
2.完成
	 */
	public Integer getOrderStatus() {
		return orderStatus;
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
	 * 设置：出发时间
	 */
	public void setGoTime(Date goTime) {
		this.goTime = goTime;
	}
	/**
	 * 获取：出发时间
	 */
	public Date getGoTime() {
		return goTime;
	}

	/**
	 * 设置：人数
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * 获取：人数
	 */
	public Integer getNumber() {
		return number;
	}
}
