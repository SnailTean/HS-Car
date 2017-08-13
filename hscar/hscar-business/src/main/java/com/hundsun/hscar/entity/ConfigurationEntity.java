package com.hundsun.hscar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author Ryan
 * @email ryansunboy@gmail.com
 * @date 2017-08-12
 */
public class ConfigurationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long configId; // 
	
	private Long userId; // 
	
	private Integer distance; // 附近距离配置(单位公里)



	/**
	 * 设置：
	 */
	public void setConfigId(Long configId) {
		this.configId = configId;
	}
	/**
	 * 获取：
	 */
	public Long getConfigId() {
		return configId;
	}

	/**
	 * 设置：
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：附近距离配置(单位公里)
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	/**
	 * 获取：附近距离配置(单位公里)
	 */
	public Integer getDistance() {
		return distance;
	}
}
