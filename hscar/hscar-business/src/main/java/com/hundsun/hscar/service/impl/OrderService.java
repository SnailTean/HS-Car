package com.hundsun.hscar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.hscar.dto.OrderDto;
import com.hundsun.hscar.entity.CarpoolingOrdersEntity;
import com.hundsun.hscar.entity.RouteDetailEntity;
import com.hundsun.hscar.service.api.IOrderService;

@Service("orderService")
public class OrderService implements IOrderService {

	@Autowired
	private RouteDetailService routeDetailService;
	@Autowired

	private CarpoolingOrdersService carpoolingOrdersService;
	@Override
	public void sendOrder(OrderDto orderDto) {
		
	}

	@Override
	public List<OrderDto> getOrderBytype(Long userId, String orderType) {
		
		List<OrderDto> orderList = new ArrayList<>();
		Map<String, Object> mapRoute =new HashMap<>();
		mapRoute.put("userId", userId);
		List<RouteDetailEntity> routeDetailList = routeDetailService.queryList(mapRoute);
		for (RouteDetailEntity routeDetailEntity : routeDetailList) {
			Map<String, Object> mapOrder =new HashMap<>();
			mapOrder.put("orderType", orderType);
			mapOrder.put("routeId", routeDetailEntity.getRouteId());
			List<CarpoolingOrdersEntity> corpoolingOrderList = carpoolingOrdersService.queryList(mapOrder);
			
		}
				return null;
	}

	@Override
	public List<OrderDto> getWaitingOrders(Long userId) {
		List<OrderDto> orderList = new ArrayList<>();
		Map<String, Object> mapRoute =new HashMap<>();
		mapRoute.put("userId", userId);
		List<RouteDetailEntity> routeDetailList = routeDetailService.queryList(mapRoute);
		for (RouteDetailEntity routeDetailEntity : routeDetailList) {
			Map<String, Object> mapOrder =new HashMap<>();
			mapOrder.put("routeId", routeDetailEntity.getRouteId());
			mapOrder.put("orderStatus", routeDetailEntity.getRouteId());

			List<CarpoolingOrdersEntity> corpoolingOrderList = carpoolingOrdersService.queryList(mapOrder);
			for (CarpoolingOrdersEntity carpoolingOrdersEntity : corpoolingOrderList) {
				OrderDto dto = new OrderDto();
				dto.setRouteDetail(routeDetailEntity);
				dto.setOrderType(carpoolingOrdersEntity.getOrderType());
				dto.setTime(null);
				orderList.add(dto);
			}
			
		}
				return orderList;
	}

	@Override
	public List<OrderDto> getSameWayOrders(Long userId) {
		
		return null;
	}

}
