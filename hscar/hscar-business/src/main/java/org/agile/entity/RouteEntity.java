package org.agile.entity;

public class RouteEntity {
	private Long routeId;
	private String departure;
	private String depCoordinate;
	private String destination;
	private String desCoordinate;
	private Long userId;
	public Long getRouteId() {
		return routeId;
	}
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getDepCoordinate() {
		return depCoordinate;
	}
	public void setDepCoordinate(String depCoordinate) {
		this.depCoordinate = depCoordinate;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDesCoordinate() {
		return desCoordinate;
	}
	public void setDesCoordinate(String desCoordinate) {
		this.desCoordinate = desCoordinate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
