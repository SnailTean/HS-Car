package com.hundsun.hscar.service.api;

import java.util.List;

import com.hundsun.hscar.dto.OrderDto;

public interface IOrderService {
	public void sendOrder(OrderDto orderDto);

	public List<OrderDto> getOrderBytype(Long userId, String orderType);

	public List<OrderDto> getWaitingOrders(Long userId);

	public List<OrderDto> getSameWayOrders(Long userId);
}
