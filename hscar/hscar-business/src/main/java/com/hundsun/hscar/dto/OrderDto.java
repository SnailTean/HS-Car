package com.hundsun.hscar.dto;

import java.util.Date;

import com.hundsun.hscar.entity.RouteDetailEntity;

public class OrderDto {
	
	private RouteDetailEntity routeDetail;
	private Integer orderType;
	private Date time;
	public RouteDetailEntity getRouteDetail() {
		return routeDetail;
	}
	public void setRouteDetail(RouteDetailEntity routeDetail) {
		this.routeDetail = routeDetail;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
