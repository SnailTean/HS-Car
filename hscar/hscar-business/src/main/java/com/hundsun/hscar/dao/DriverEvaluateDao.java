package com.hundsun.hscar.dao;

import java.math.BigDecimal;

import org.agile.dao.BaseDao;

import com.hundsun.hscar.entity.DriverEvaluateEntity;

/**
 * 司机评价表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
public interface DriverEvaluateDao extends BaseDao<DriverEvaluateEntity> {
	
	/**
	 * 获取司机评分
	 * @param driverId 司机Id
	 */
	BigDecimal getDriverRate(Long driverId);
	
}
