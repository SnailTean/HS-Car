package com.hundsun.hscar.service.api;

import com.hundsun.hscar.entity.DriverEntity;

import java.util.List;
import java.util.Map;

/**
 * 司机信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
public interface IDriverService {
	
	DriverEntity queryObjectById(Long driverId);
	
	DriverEntity queryObject(DriverEntity driver);
	
	List<DriverEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DriverEntity driver);
	
	void update(DriverEntity driver);
	
	void delete(Long driverId);
	
	void deleteBatch(Long[] driverIds);
}
