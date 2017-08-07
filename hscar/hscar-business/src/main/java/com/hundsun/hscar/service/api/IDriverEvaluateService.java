package com.hundsun.hscar.service.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hundsun.hscar.entity.DriverEvaluateEntity;

/**
 * 司机评价表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
public interface IDriverEvaluateService {
	
	DriverEvaluateEntity queryObjectById(Integer id);
	
	DriverEvaluateEntity queryObject(DriverEvaluateEntity driverEvaluate);
	
	List<DriverEvaluateEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DriverEvaluateEntity driverEvaluate);
	
	void update(DriverEvaluateEntity driverEvaluate);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	/**
	 * 获取司机评分
	 * @param driverId 司机Id
	 */
	BigDecimal getDriverRate(Long driverId);
}
