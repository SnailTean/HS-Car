package com.hundsun.hscar.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.agile.common.exception.RRException;
import org.agile.common.validator.Assert;
import org.agile.constant.Constant;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.hscar.dao.UserDao;
import com.hundsun.hscar.entity.ConfigurationEntity;
import com.hundsun.hscar.entity.UserEntity;
import com.hundsun.hscar.service.api.IConfigurationService;
import com.hundsun.hscar.service.api.IUserService;

/**
 * 用户信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private IConfigurationService configurationService;
	@Override
	public UserEntity queryObjectById(Long userId){
		UserEntity user = new UserEntity();
		user.setUserId(userId);
		return userDao.queryObject(user);
	}
	
	@Override
	public UserEntity queryObjectByMobile(String mobile) {
		UserEntity user = new UserEntity();
		user.setMobile(mobile);
		return userDao.queryObject(user);
	}
	
	@Override
	public UserEntity queryObject(UserEntity user){
		return userDao.queryObject(user);
	}
	
	@Override
	public List<UserEntity> queryList(Map<String, Object> map){
		return userDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userDao.queryTotal(map);
	}
	
	@Override
	public void save(UserEntity user){
		user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
		user.setCreateTime(new Date());
		userDao.save(user);
	}
	
	@Override
	public void update(UserEntity user){
		user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
		user.setUpdateTime(new Date());
		userDao.update(user);
	}
	
	@Override
	public void delete(Long userId){
		userDao.delete(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		userDao.deleteBatch(userIds);
	}
	
	/**
	 * 乘客用户注册
	 * @param mobile    手机号
	 * @param password  密码
	 */
	@Override
	public UserEntity register(String mobile, String password){
		UserEntity user = new UserEntity();
		user.setMobile(mobile);
		user.setUsername(mobile);
		user.setPassword(DigestUtils.sha256Hex(password));
		user.setCreateTime(new Date());
		userDao.save(user);
		ConfigurationEntity configuration = new ConfigurationEntity();
		configuration.setUserId(user.getUserId());
		configuration.setDistance(Constant.DEFAULT_DISTANCE);
		configurationService.save(configuration);
		return user;
	}

	/**
	 * 乘客用户登录
	 * @param mobile    手机号
	 * @param password  密码
	 * @return          返回用户ID
	 */
	@Override
	public long login(String mobile, String password) {
		UserEntity user = queryObjectByMobile(mobile);
		Assert.isNull(user, "手机号或密码错误");

		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(password))){
			throw new RRException("手机号或密码错误");
		}

		return user.getUserId();
	}
}
