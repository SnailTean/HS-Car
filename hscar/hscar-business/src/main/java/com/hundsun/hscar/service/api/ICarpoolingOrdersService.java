package com.hundsun.hscar.service.api;

import com.hundsun.hscar.entity.CarpoolingOrdersEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
public interface ICarpoolingOrdersService {
	
	CarpoolingOrdersEntity queryObjectById(Long orderId);
	
	CarpoolingOrdersEntity queryObject(CarpoolingOrdersEntity carpoolingOrders);
	
	List<CarpoolingOrdersEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CarpoolingOrdersEntity carpoolingOrders);
	
	void update(CarpoolingOrdersEntity carpoolingOrders);
	
	void delete(Long orderId);
	
	void deleteBatch(Long[] orderIds);
}
