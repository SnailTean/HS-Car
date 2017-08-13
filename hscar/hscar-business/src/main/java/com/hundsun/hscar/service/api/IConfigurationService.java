package com.hundsun.hscar.service.api;

import com.hundsun.hscar.entity.ConfigurationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Ryan
 * @email ryansunboy@gmail.com
 * @date 2017-08-12
 */
public interface IConfigurationService {
	
	ConfigurationEntity queryObjectById(Long configId);
	
	ConfigurationEntity queryObject(ConfigurationEntity configuration);
	
	List<ConfigurationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ConfigurationEntity configuration);
	
	void update(ConfigurationEntity configuration);
	
	void delete(Long configId);
	
	void deleteBatch(Long[] configIds);
}
