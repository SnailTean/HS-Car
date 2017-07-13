package com.hundsun.hscar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hundsun.hscar.dao.RouteDetailDao;
import com.hundsun.hscar.entity.RouteDetailEntity;
import com.hundsun.hscar.service.api.IRouteDetailService;



@Service("routeDetailService")
public class RouteDetailService implements IRouteDetailService {
	
	@Autowired
	private RouteDetailDao routeDetailDao;
	
	@Override
	public RouteDetailEntity queryObject(Long routeId){
		return routeDetailDao.queryObject(routeId);
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
	
}
