package com.hundsun.hscar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 用户信息表
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-07-29
 */
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long userId; // 
	
	private String username; // 用户名
	
	private String mobile; // 手机号
	
	private String password; // 密码
	
	private String nickName; // 昵称
	
	private Integer age; // 年龄
	
	private String sex; // 性别
	
	private String identityCard; // 身份证
	
	private String mail; // 邮箱
	
	private String occupation; // 职业
	
	private String trade; // 行业
	
	private String company; // 公司
	
	private String signature; // 各项签名
	
	private String avatar; // 头像
	
	private Date createTime; // 创建时间
	
	private Date updateTime; // 更新时间



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
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置：昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置：身份证
	 */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	/**
	 * 获取：身份证
	 */
	public String getIdentityCard() {
		return identityCard;
	}

	/**
	 * 设置：邮箱
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * 获取：邮箱
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * 设置：职业
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	/**
	 * 获取：职业
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * 设置：行业
	 */
	public void setTrade(String trade) {
		this.trade = trade;
	}
	/**
	 * 获取：行业
	 */
	public String getTrade() {
		return trade;
	}

	/**
	 * 设置：公司
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 获取：公司
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 设置：各项签名
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	/**
	 * 获取：各项签名
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 设置：头像
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * 获取：头像
	 */
	public String getAvatar() {
		return avatar;
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
