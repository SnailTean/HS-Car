package com.hundsun.hscar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 司机评价表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
public class DriverEvaluateEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id; // 
	
	private Integer stars; // 评星
	
	private Integer driverId; // 
	
	private String content; // 评价内容
	
	private Date createTime; // 创建时间
	
	private Date updateTime; // 更新时间



	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置：评星
	 */
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	/**
	 * 获取：评星
	 */
	public Integer getStars() {
		return stars;
	}

	/**
	 * 设置：
	 */
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	/**
	 * 获取：
	 */
	public Integer getDriverId() {
		return driverId;
	}

	/**
	 * 设置：评价内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评价内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
