package com.hundsun.hscar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hundsun.hscar.dao.DriverDao;
import com.hundsun.hscar.entity.DriverEntity;
import com.hundsun.hscar.service.api.IDriverService;

/**
 * 司机信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
@Service("driverService")
public class DriverService implements IDriverService {
	
	@Autowired
	private DriverDao driverDao;
	
	@Override
	public DriverEntity queryObjectById(Long driverId){
		DriverEntity driver = new DriverEntity();
		driver.setDriverId(driverId);
		return driverDao.queryObject(driver);
	}
	
	@Override
	public DriverEntity queryObject(DriverEntity driver){
		return driverDao.queryObject(driver);
	}
	
	@Override
	public List<DriverEntity> queryList(Map<String, Object> map){
		return driverDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return driverDao.queryTotal(map);
	}
	
	@Override
	public void save(DriverEntity driver){
		driverDao.save(driver);
	}
	
	@Override
	public void update(DriverEntity driver){
		driverDao.update(driver);
	}
	
	@Override
	public void delete(Long driverId){
		driverDao.delete(driverId);
	}
	
	@Override
	public void deleteBatch(Long[] driverIds){
		driverDao.deleteBatch(driverIds);
	}
	
}
