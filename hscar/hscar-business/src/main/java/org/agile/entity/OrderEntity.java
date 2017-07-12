package org.agile.entity;

public class OrderEntity {
	private Long orderId;
	private Integer orderType;
	private Double price;
	private Double reward;
	private RouteEntity routeEntity;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getReward() {
		return reward;
	}
	public void setReward(Double reward) {
		this.reward = reward;
	}
	public RouteEntity getRouteEntity() {
		return routeEntity;
	}
	public void setRouteEntity(RouteEntity routeEntity) {
		this.routeEntity = routeEntity;
	}
	
	
}
