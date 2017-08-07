package com.hundsun.hscar.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.hscar.dao.DriverEvaluateDao;
import com.hundsun.hscar.entity.DriverEvaluateEntity;
import com.hundsun.hscar.service.api.IDriverEvaluateService;

/**
 * 司机评价表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
@Service("driverEvaluateService")
public class DriverEvaluateService implements IDriverEvaluateService {
	
	@Autowired
	private DriverEvaluateDao driverEvaluateDao;
	
	@Override
	public DriverEvaluateEntity queryObjectById(Integer id){
		DriverEvaluateEntity driverEvaluate = new DriverEvaluateEntity();
		driverEvaluate.setId(id);
		return driverEvaluateDao.queryObject(driverEvaluate);
	}
	
	@Override
	public DriverEvaluateEntity queryObject(DriverEvaluateEntity driverEvaluate){
		return driverEvaluateDao.queryObject(driverEvaluate);
	}
	
	@Override
	public List<DriverEvaluateEntity> queryList(Map<String, Object> map){
		return driverEvaluateDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return driverEvaluateDao.queryTotal(map);
	}
	
	@Override
	public void save(DriverEvaluateEntity driverEvaluate){
		driverEvaluateDao.save(driverEvaluate);
	}
	
	@Override
	public void update(DriverEvaluateEntity driverEvaluate){
		driverEvaluateDao.update(driverEvaluate);
	}
	
	@Override
	public void delete(Integer id){
		driverEvaluateDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		driverEvaluateDao.deleteBatch(ids);
	}
	
	/**
	 * 获取司机评分
	 * @param driverId 司机Id
	 */
	public BigDecimal getDriverRate(Long driverId){
		return driverEvaluateDao.getDriverRate(driverId);
	}
	
}
