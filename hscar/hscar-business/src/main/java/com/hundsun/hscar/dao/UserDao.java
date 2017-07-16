package com.hundsun.hscar.dao;

import org.agile.dao.BaseDao;

import com.hundsun.hscar.entity.UserEntity;

/**
 * 用户信息
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
public interface UserDao extends BaseDao<UserEntity> {
	
	UserEntity queryByMobile(String mobile);
	
}
