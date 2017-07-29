package com.hundsun.hscar.service.api;

import com.hundsun.hscar.entity.DriverEvaluateEntity;

import java.util.List;
import java.util.Map;

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
}
