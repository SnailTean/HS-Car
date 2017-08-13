package com.hundsun.hscar.dao;

import java.util.List;
import java.util.Map;

import org.agile.dao.BaseDao;

import com.hundsun.hscar.entity.RouteDetailEntity;

/**
 * 路线详情表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
public interface RouteDetailDao extends BaseDao<RouteDetailEntity> {

	List<RouteDetailEntity> querySameWayOrders(Map<String, Object> map);
	
}
