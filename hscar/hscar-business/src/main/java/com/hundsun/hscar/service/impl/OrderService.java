package com.hundsun.hscar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.agile.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.hscar.constant.OrderStatusEnum;
import com.hundsun.hscar.constant.RouteStatusEnum;
import com.hundsun.hscar.dto.BaseOrderDto;
import com.hundsun.hscar.dto.OrderDto;
import com.hundsun.hscar.dto.WaitingOrderDto;
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
	public List<OrderDto> getSameWayOrders(Long userId) {
		
		return null;
	}

	@Override
	public WaitingOrderDto getWaitingOrder(Long userId) {
		WaitingOrderDto dto = new WaitingOrderDto();
	
		RouteDetailEntity routeDetailReq = new RouteDetailEntity();
		routeDetailReq.setUserId(userId);
		routeDetailReq.setRouteStatus(RouteStatusEnum.ACTIVED.getValue());
		RouteDetailEntity routeDetailRes= routeDetailService.queryObject(routeDetailReq);
		if(CommonUtils.isEmpty(routeDetailRes)){
			return dto;
		}
		dto.setDestination(routeDetailRes.getDestination());
		CarpoolingOrdersEntity orderQuery = new CarpoolingOrdersEntity();
		orderQuery.setRouteId(routeDetailRes.getRouteId());
		orderQuery.setOrderStatus(OrderStatusEnum.PROCESSING.getValue());
		CarpoolingOrdersEntity corpoolingOrderRes = carpoolingOrdersService.queryObject(orderQuery);
		if(CommonUtils.isEmpty(corpoolingOrderRes)){
			orderQuery.setOrderStatus(OrderStatusEnum.PUBLISHING.getValue());
			corpoolingOrderRes = carpoolingOrdersService.queryObject(orderQuery);
			if(CommonUtils.isEmpty(corpoolingOrderRes)){
				return dto;
			}
		}
	
		dto.setDeparture(routeDetailRes.getDeparture());
		dto.setOrderId(corpoolingOrderRes.getOrderId());
		dto.setGoTime(corpoolingOrderRes.getGoTime());
		dto.setOrderStatus(corpoolingOrderRes.getOrderStatus());
		dto.setOrderType(corpoolingOrderRes.getOrderType());
		dto.setPrice(corpoolingOrderRes.getPrice());
		dto.setReward(corpoolingOrderRes.getReward());
		dto.setNum(corpoolingOrderRes.getNumber());
		return dto;
	}

	@Override
	public List<BaseOrderDto> getCompleteOrders(Long userId) {
		List<BaseOrderDto>  dtos = new ArrayList<>();
		HashMap<String, Object> routeQuery = new HashMap<>();
		routeQuery.put("userId", userId);
		routeQuery.put("routeStatus", RouteStatusEnum.EXPIRED.getValue());
	
		List<RouteDetailEntity> routeDetailResList= routeDetailService.queryList(routeQuery);
		if(CommonUtils.isEmpty(routeDetailResList)){
			return dtos;
		}
		
		for (RouteDetailEntity routeDetailRes : routeDetailResList) {
			
			CarpoolingOrdersEntity orderQuery = new CarpoolingOrdersEntity();
			orderQuery.setRouteId(routeDetailRes.getRouteId());
			orderQuery.setOrderStatus(OrderStatusEnum.COMPLETE.getValue());
			CarpoolingOrdersEntity corpoolingOrderRes = carpoolingOrdersService.queryObject(orderQuery);
			if(CommonUtils.isEmpty(corpoolingOrderRes)){
				return dtos;
			}
			BaseOrderDto dto = new BaseOrderDto();
			dto.setDeparture(routeDetailRes.getDeparture());
			dto.setOrderId(corpoolingOrderRes.getOrderId());
			dto.setGoTime(corpoolingOrderRes.getGoTime());
			dto.setOrderStatus(corpoolingOrderRes.getOrderStatus());
			dto.setOrderType(corpoolingOrderRes.getOrderType());
			dto.setPrice(corpoolingOrderRes.getPrice());
			dto.setReward(corpoolingOrderRes.getReward());
			dto.setNum(corpoolingOrderRes.getNumber());
			dto.setDestination(routeDetailRes.getDestination());
			dtos.add(dto);
		}
		return dtos;
	}

}
