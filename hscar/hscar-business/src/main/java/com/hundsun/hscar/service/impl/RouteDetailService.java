package com.hundsun.hscar.service.impl;

import org.agile.dto.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hundsun.hscar.constant.RouteStatusEnum;
import com.hundsun.hscar.dao.RouteDetailDao;
import com.hundsun.hscar.entity.RouteDetailEntity;
import com.hundsun.hscar.service.api.IRouteDetailService;

/**
 * 路线详情表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
@Service("routeDetailService")
public class RouteDetailService implements IRouteDetailService {
	
	@Autowired
	private RouteDetailDao routeDetailDao;
	
	@Override
	public RouteDetailEntity queryObjectById(Long routeId){
		RouteDetailEntity routeDetail = new RouteDetailEntity();
		routeDetail.setRouteId(routeId);
		return routeDetailDao.queryObject(routeDetail);
	}
	
	@Override
	public RouteDetailEntity queryObject(RouteDetailEntity routeDetail){
		return routeDetailDao.queryObject(routeDetail);
	}
	
	@Override
	public List<RouteDetailEntity> queryList(Map<String, Object> map){
		return routeDetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return routeDetailDao.queryTotal(map);
	}
	
	@Override
	public void save(RouteDetailEntity routeDetail){
		routeDetailDao.save(routeDetail);
	}
	
	@Override
	public void update(RouteDetailEntity routeDetail){
		routeDetailDao.update(routeDetail);
	}
	
	@Override
	public void delete(Long routeId){
		routeDetailDao.delete(routeId);
	}
	
	@Override
	public void deleteBatch(Long[] routeIds){
		routeDetailDao.deleteBatch(routeIds);
	}

	public RouteDetailEntity queryActiveRouteDetail(Long userId) {
		RouteDetailEntity routeQuery = new RouteDetailEntity();
		routeQuery.setUserId(userId);
		routeQuery.setRouteStatus(RouteStatusEnum.ACTIVED.getValue());
		return routeDetailDao.queryObject(routeQuery);
		
	}

	public List<RouteDetailEntity> querySameWayOrders(Long userId,Integer userType, double lRight, double lLeft) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("lRight", lRight);
		map.put("lLeft", lLeft);
		map.put("userType", userType);
		return routeDetailDao.querySameWayOrders(map);
	}
	
}
