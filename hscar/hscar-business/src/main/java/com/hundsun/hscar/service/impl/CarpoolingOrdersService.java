package com.hundsun.hscar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hundsun.hscar.dao.CarpoolingOrdersDao;
import com.hundsun.hscar.entity.CarpoolingOrdersEntity;
import com.hundsun.hscar.service.api.ICarpoolingOrdersService;



@Service("carpoolingOrdersService")
public class CarpoolingOrdersService implements ICarpoolingOrdersService {
	
	@Autowired
	private CarpoolingOrdersDao carpoolingOrdersDao;
	
	@Override
	public CarpoolingOrdersEntity queryObject(Long orderId){
		return carpoolingOrdersDao.queryObject(orderId);
	}
	
	@Override
	public List<CarpoolingOrdersEntity> queryList(Map<String, Object> map){
		return carpoolingOrdersDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return carpoolingOrdersDao.queryTotal(map);
	}
	
	@Override
	public void save(CarpoolingOrdersEntity carpoolingOrders){
		carpoolingOrdersDao.save(carpoolingOrders);
	}
	
	@Override
	public void update(CarpoolingOrdersEntity carpoolingOrders){
		carpoolingOrdersDao.update(carpoolingOrders);
	}
	
	@Override
	public void delete(Long orderId){
		carpoolingOrdersDao.delete(orderId);
	}
	
	@Override
	public void deleteBatch(Long[] orderIds){
		carpoolingOrdersDao.deleteBatch(orderIds);
	}
	
}
