package com.hundsun.hscar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hundsun.hscar.dao.FriendshipDao;
import com.hundsun.hscar.entity.FriendshipEntity;
import com.hundsun.hscar.service.api.IFriendshipService;

/**
 * 好友表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
@Service("friendshipService")
public class FriendshipService implements IFriendshipService {
	
	@Autowired
	private FriendshipDao friendshipDao;
	
	@Override
	public FriendshipEntity queryObjectById(Integer id){
		FriendshipEntity friendship = new FriendshipEntity();
		friendship.setId(id);
		return friendshipDao.queryObject(friendship);
	}
	
	@Override
	public FriendshipEntity queryObject(FriendshipEntity friendship){
		return friendshipDao.queryObject(friendship);
	}
	
	@Override
	public List<FriendshipEntity> queryList(Map<String, Object> map){
		return friendshipDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return friendshipDao.queryTotal(map);
	}
	
	@Override
	public void save(FriendshipEntity friendship){
		friendshipDao.save(friendship);
	}
	
	@Override
	public void update(FriendshipEntity friendship){
		friendshipDao.update(friendship);
	}
	
	@Override
	public void delete(Integer id){
		friendshipDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		friendshipDao.deleteBatch(ids);
	}
	
}
