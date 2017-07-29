package com.hundsun.hscar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 好友表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
public class FriendshipEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id; // 
	
	private Integer userId; // 
	
	private Integer friendId; // 
	
	private Integer group; // 朋友分组字段 1.好友2.拼友3.司机
	
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
	 * 设置：
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置：
	 */
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
	/**
	 * 获取：
	 */
	public Integer getFriendId() {
		return friendId;
	}

	/**
	 * 设置：朋友分组字段 1.好友2.拼友3.司机
	 */
	public void setGroup(Integer group) {
		this.group = group;
	}
	/**
	 * 获取：朋友分组字段 1.好友2.拼友3.司机
	 */
	public Integer getGroup() {
		return group;
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
