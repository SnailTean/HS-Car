package com.hundsun.hscar.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.agile.common.exception.RRException;
import org.agile.common.validator.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hundsun.hscar.dao.DriverDao;
import com.hundsun.hscar.entity.DriverEntity;
import com.hundsun.hscar.entity.UserEntity;
import com.hundsun.hscar.service.api.IDriverService;
import com.hundsun.hscar.service.api.IUserService;

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
	
	@Autowired
    private IUserService userService;
	
	@Override
	public DriverEntity queryObjectById(Long driverId){
		DriverEntity driver = new DriverEntity();
		driver.setDriverId(driverId);
		return driverDao.queryObject(driver);
	}
	
	@Override
	public DriverEntity queryObjectByUserId(Long userId){
		DriverEntity driver = new DriverEntity();
		driver.setUserId(userId);
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
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void register(String mobile, String password, String driverLicenseNumber, String plateNumber) {
		UserEntity user = userService.register(mobile, password);
		DriverEntity driver = new DriverEntity();
		driver.setUserId(user.getUserId());
		driver.setDriverLicenseNumber(driverLicenseNumber);
		driver.setPlateNumber(plateNumber);
		driver.setCreateTime(new Date());
		driverDao.save(driver);
	}

	@Override
	public long login(String mobile, String password) {
		UserEntity user = userService.queryObjectByMobile(mobile);
		Assert.isNull(user, "手机号或密码错误");

		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(password))){
			throw new RRException("手机号或密码错误");
		}
		
		//查询是否存在车主记录
        DriverEntity driverEntity = this.queryObjectByUserId(user.getUserId());
        if(driverEntity == null) {
            throw new RRException("不存在车主记录，请注册");
        }

		return user.getUserId();
	}
}
