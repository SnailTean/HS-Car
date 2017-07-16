package com.hundsun.hscar.service.api;

import com.hundsun.hscar.entity.RouteDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 路线详情表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
public interface IRouteDetailService {
	
	RouteDetailEntity queryObjectById(Long routeId);
	
	RouteDetailEntity queryObject(RouteDetailEntity routeDetail);
	
	List<RouteDetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RouteDetailEntity routeDetail);
	
	void update(RouteDetailEntity routeDetail);
	
	void delete(Long routeId);
	
	void deleteBatch(Long[] routeIds);
}
