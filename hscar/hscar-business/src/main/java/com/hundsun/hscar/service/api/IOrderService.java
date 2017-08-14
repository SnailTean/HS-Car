package com.hundsun.hscar.service.api;

import com.hundsun.hscar.dto.BaseOrderDto;
import com.hundsun.hscar.dto.CarOrderDto;
import com.hundsun.hscar.dto.WaitingOrderDto;
import com.hundsun.hscar.entity.UserEntity;

import java.util.List;

public interface IOrderService {
	public void sendOrder(CarOrderDto carOrderDto,UserEntity user);

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
