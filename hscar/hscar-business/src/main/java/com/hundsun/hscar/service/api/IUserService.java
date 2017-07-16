package com.hundsun.hscar.service.api;

import java.util.List;
import java.util.Map;

import com.hundsun.hscar.entity.UserEntity;

/**
 * 用户信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
public interface IUserService {
	
	UserEntity queryObjectById(Long userId);
	
	UserEntity queryObjectByMobile(String mobile);
	
	UserEntity queryObject(UserEntity user);
	
	List<UserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserEntity user);
	
	void update(UserEntity user);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);
	
	/**
	 * 顾客用户注册
	 * @param mobile    手机号
	 * @param password  密码
	 */
	UserEntity register(String mobile, String password);

	/**
	 * 顾客用户登录
	 * @param mobile    手机号
	 * @param password  密码
	 * @return          返回用户ID
	 */
	long login(String mobile, String password);
}
