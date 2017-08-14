package com.hundsun.hscar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hundsun.hscar.dao.ConfigurationDao;
import com.hundsun.hscar.entity.ConfigurationEntity;
import com.hundsun.hscar.service.api.IConfigurationService;

/**
 * 
 * 
 * @author Ryan
 * @email ryansunboy@gmail.com
 * @date 2017-08-12
 */
@Service("configurationService")
public class ConfigurationService implements IConfigurationService {
	
	@Autowired
	private ConfigurationDao configurationDao;
	
	@Override
	public ConfigurationEntity queryObjectById(Long configId){
		ConfigurationEntity configuration = new ConfigurationEntity();
		configuration.setConfigId(configId);
		return configurationDao.queryObject(configuration);
	}
	
	@Override
	public ConfigurationEntity queryObject(ConfigurationEntity configuration){
		return configurationDao.queryObject(configuration);
	}
	
	@Override
	public List<ConfigurationEntity> queryList(Map<String, Object> map){
		return configurationDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return configurationDao.queryTotal(map);
	}
	
	@Override
	public void save(ConfigurationEntity configuration){
		configurationDao.save(configuration);
	}
	
	@Override
	public void update(ConfigurationEntity configuration){
		configurationDao.update(configuration);
	}
	
	@Override
	public void delete(Long configId){
		configurationDao.delete(configId);
	}
	
	@Override
	public void deleteBatch(Long[] configIds){
		configurationDao.deleteBatch(configIds);
	}
	
}
