package com.hundsun.hscar.service.api;

import com.hundsun.hscar.entity.TokenEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户Token
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-16
 */
public interface ITokenService {
	
	TokenEntity queryObjectById(Long userId);
	
	TokenEntity queryObject(TokenEntity token);
	
	List<TokenEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TokenEntity token);
	
	void update(TokenEntity token);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);
	
	TokenEntity queryByUserId(Long userId);

	TokenEntity queryByToken(String token);
	
	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token相关信息
	 */
	Map<String, Object> createToken(long userId);
}
