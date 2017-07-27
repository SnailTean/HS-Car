package com.hundsun.hscar.vo;

import java.util.Date;

public class OrderVo {
	private RouteDetailVo routeDetail;
	private Date time;
	private Integer orderType;
	public RouteDetailVo getRouteDetail() {
		return routeDetail;
	}
	public void setRouteDetail(RouteDetailVo routeDetail) {
		this.routeDetail = routeDetail;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	
	
}
