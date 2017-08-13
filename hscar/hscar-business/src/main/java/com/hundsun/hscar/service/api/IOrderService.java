package com.hundsun.hscar.service.api;

import java.util.List;

import com.hundsun.hscar.dto.BaseOrderDto;
import com.hundsun.hscar.dto.OrderDto;
import com.hundsun.hscar.dto.WaitingOrderDto;

public interface IOrderService {
	public void sendOrder(OrderDto orderDto);

	public List<OrderDto> getOrderBytype(Long userId, String orderType);

	public List<WaitingOrderDto> getSameWayOrders(Long userId, Integer userType);

	/**
	 * 获取待处理订单信息
	 * @param userId
	 * @return
	 */
	public WaitingOrderDto getWaitingOrder(Long userId);

	/**
	 * 获取完成订单信息
	 * @param userId
	 * @return
	 */
	public List<BaseOrderDto> getCompleteOrders(Long userId);

	
}
