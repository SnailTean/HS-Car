package com.hundsun.hscar.service.api;

import com.hundsun.hscar.entity.FriendshipEntity;

import java.util.List;
import java.util.Map;

/**
 * 好友表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
public interface IFriendshipService {
	
	FriendshipEntity queryObjectById(Integer id);
	
	FriendshipEntity queryObject(FriendshipEntity friendship);
	
	List<FriendshipEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(FriendshipEntity friendship);
	
	void update(FriendshipEntity friendship);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
