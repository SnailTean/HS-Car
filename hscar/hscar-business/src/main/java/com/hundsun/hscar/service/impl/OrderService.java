package com.hundsun.hscar.service.impl;

import java.util.*;

import com.hundsun.hscar.dto.CarOrderDto;
import com.hundsun.hscar.entity.UserEntity;
import org.agile.common.utils.BdMapUtils;
import org.agile.common.utils.CommonUtils;
import org.agile.dto.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.hscar.constant.OrderStatusEnum;
import com.hundsun.hscar.constant.RouteStatusEnum;
import com.hundsun.hscar.dto.BaseOrderDto;
import com.hundsun.hscar.dto.WaitingOrderDto;
import com.hundsun.hscar.entity.CarpoolingOrdersEntity;
import com.hundsun.hscar.entity.ConfigurationEntity;
import com.hundsun.hscar.entity.RouteDetailEntity;
import com.hundsun.hscar.service.api.IOrderService;

@Service("orderService")
public class OrderService implements IOrderService {

	@Autowired
	private RouteDetailService routeDetailService;
	@Autowired
	private CarpoolingOrdersService carpoolingOrdersService;
	@Autowired
	private ConfigurationService configurationService;
	@Override
	public void sendOrder(CarOrderDto carOrderDto,UserEntity user) {
		RouteDetailEntity routeDetailEntity = tansferDtoToRouteDetailEntity(carOrderDto, user);
		routeDetailService.save(routeDetailEntity);
		CarpoolingOrdersEntity carpoolingOrdersEntity = tansferDtoToCarpoolingOrdersEntity(carOrderDto, routeDetailEntity);
		carpoolingOrdersService.save(carpoolingOrdersEntity);
	}


	@Override
	public List<WaitingOrderDto> getSameWayOrders(Long userId,Integer userType) {
		
		List<WaitingOrderDto> dtos = new ArrayList<>();
		
		RouteDetailEntity routeDetail = routeDetailService.queryActiveRouteDetail(userId);
		if(CommonUtils.isEmpty(routeDetail)){
			return dtos;
		}
		ConfigurationEntity configuration = new ConfigurationEntity();
		configuration.setUserId(userId);
		configuration = configurationService.queryObject(configuration);
		LocationDto lRight =BdMapUtils.getLocation(routeDetail.getDepLatitude(), routeDetail.getDepLongitude(), configuration.getDistance(), 45);
		LocationDto lLeft =BdMapUtils.getLocation(routeDetail.getDepLatitude(), routeDetail.getDepLongitude(), configuration.getDistance(), 45);

		List<RouteDetailEntity> routeDetailList= routeDetailService.querySameWayOrders(userId,userType,lRight.getLatitude(),lLeft.getLatitude());
		for (RouteDetailEntity routeDetailEntity : routeDetailList) {
			CarpoolingOrdersEntity carpoolingOrder = new CarpoolingOrdersEntity();
			carpoolingOrder.setRouteId(routeDetailEntity.getRouteId());
			carpoolingOrder.setOrderStatus(OrderStatusEnum.PUBLISHING.getValue());
			 carpoolingOrder = carpoolingOrdersService.queryObject(carpoolingOrder);
			 if(CommonUtils.isNotEmpty(carpoolingOrder)){
				 double distance = BdMapUtils.getDistanceFromTwoPoints(routeDetail.getDepLatitude(), routeDetail.getDesLatitude(), routeDetailEntity.getDepLatitude(), routeDetailEntity.getDepLongitude());
					WaitingOrderDto dto = trasnferOrderEntityToWaitingOrderDto(carpoolingOrder,routeDetailEntity,distance);
					if(CommonUtils.isNotEmpty(dto)){
						dtos.add(dto);
					}
			 }
		}
		return dtos;
		
		
	}

	private WaitingOrderDto trasnferOrderEntityToWaitingOrderDto(CarpoolingOrdersEntity carpoolingOrder,
			RouteDetailEntity routeDetailEntity, double distance) {
		WaitingOrderDto dto = new WaitingOrderDto();
		if(CommonUtils.isEmpty(routeDetailEntity)||CommonUtils.isEmpty(carpoolingOrder)){
			return dto;
		}
		dto.setDeparture(routeDetailEntity.getDeparture());
		dto.setDestination(routeDetailEntity.getDestination());
		dto.setOrderId(carpoolingOrder.getOrderId());
		dto.setGoTime(carpoolingOrder.getGoTime());
		dto.setOrderStatus(carpoolingOrder.getOrderStatus());
		dto.setOrderType(carpoolingOrder.getOrderType());
		dto.setPrice(carpoolingOrder.getPrice());
		dto.setReward(carpoolingOrder.getReward());
		dto.setNum(carpoolingOrder.getNumber());
		dto.setDistance(distance);

		return dto;
	}

	@Override
	public WaitingOrderDto getWaitingOrder(Long userId) {
		WaitingOrderDto dto = new WaitingOrderDto();
		
		RouteDetailEntity routeDetailRes= routeDetailService.queryActiveRouteDetail(userId);
		if(CommonUtils.isEmpty(routeDetailRes)){
			return dto;
		}
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
		dto = trasnferOrderEntityToWaitingOrderDto(corpoolingOrderRes,routeDetailRes,0);
		
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
			BaseOrderDto dto =transferEntityToBaseOrderDto(routeDetailRes,corpoolingOrderRes);
			if(CommonUtils.isNotEmpty(dto)){
				dtos.add(dto);
			}
		}
		return dtos;
	}

	private BaseOrderDto transferEntityToBaseOrderDto(RouteDetailEntity routeDetailRes,
			CarpoolingOrdersEntity corpoolingOrderRes) {
		
		BaseOrderDto dto = new BaseOrderDto();
		if(CommonUtils.isEmpty(routeDetailRes)||CommonUtils.isEmpty(corpoolingOrderRes)){
			return dto;
		}
		dto.setDeparture(routeDetailRes.getDeparture());
		dto.setOrderId(corpoolingOrderRes.getOrderId());
		dto.setGoTime(corpoolingOrderRes.getGoTime());
		dto.setOrderStatus(corpoolingOrderRes.getOrderStatus());
		dto.setOrderType(corpoolingOrderRes.getOrderType());
		dto.setPrice(corpoolingOrderRes.getPrice());
		dto.setReward(corpoolingOrderRes.getReward());
		dto.setNum(corpoolingOrderRes.getNumber());
		dto.setDestination(routeDetailRes.getDestination());
		return dto;
	}

	private CarpoolingOrdersEntity tansferDtoToCarpoolingOrdersEntity(CarOrderDto carOrderDto, RouteDetailEntity routeDetailEntity) {
		CarpoolingOrdersEntity carpoolingOrdersEntity = new CarpoolingOrdersEntity();
		carpoolingOrdersEntity.setGoTime(carOrderDto.getGoTime());
		carpoolingOrdersEntity.setNumber(carOrderDto.getNum());
		carpoolingOrdersEntity.setPrice(carOrderDto.getPrice());
		carpoolingOrdersEntity.setRouteId(routeDetailEntity.getRouteId());
		carpoolingOrdersEntity.setCreateTime(new Date());
		carpoolingOrdersEntity.setUpdateTime(new Date());
		carpoolingOrdersEntity.setOrderType(carOrderDto.getOrderType());
		carpoolingOrdersEntity.setOrderStatus(carOrderDto.getOrderStatus());
		return carpoolingOrdersEntity;
	}

	private RouteDetailEntity tansferDtoToRouteDetailEntity(CarOrderDto carOrderDto, UserEntity user) {
		RouteDetailEntity routeDetailEntity = new RouteDetailEntity();
		routeDetailEntity.setDeparture(carOrderDto.getDeparture());
		routeDetailEntity.setDestination(carOrderDto.getDestination());
		routeDetailEntity.setUserId(user.getUserId());
		routeDetailEntity.setRouteStatus(RouteStatusEnum.ACTIVED.getValue());
		routeDetailEntity.setUserType(carOrderDto.getUserType());
		routeDetailEntity.setUpdateTime(new Date());
		routeDetailEntity.setCreateTime(new Date());
		routeDetailEntity.setDepLatitude(carOrderDto.getDepLatitude());
		routeDetailEntity.setDepLongitude(carOrderDto.getDepLongitude());
		routeDetailEntity.setDesLongitude(carOrderDto.getDesLongitude());
		routeDetailEntity.setDesLatitude(carOrderDto.getDesLatitude());
		return routeDetailEntity;
	}
}
