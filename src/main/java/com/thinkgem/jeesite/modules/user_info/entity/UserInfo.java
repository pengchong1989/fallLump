/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.user_info.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户信息Entity
 * @author pengc
 * @version 2016-06-16
 */
public class UserInfo extends DataEntity<UserInfo> {
	
	private static final long serialVersionUID = 1L;
	private String username;		// 用户名
	private String tradePassword;		// 交易密码
	private String email;		// email
	private String nickname;		// 昵称
	private String realname;		// 真实姓名
	private String identifyCardNo;		// 身份证号
	private String mobile;		// 手机号
	private String ismailvalidate;		// 邮箱是否验证
	
	public UserInfo() {
		super();
	}

	public UserInfo(String id){
		super(id);
	}

	@Length(min=1, max=64, message="用户名长度必须介于 1 和 64 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=255, message="交易密码长度必须介于 1 和 255 之间")
	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}
	
	@Length(min=0, max=64, message="email长度必须介于 0 和 64 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=64, message="昵称长度必须介于 0 和 64 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=1, max=64, message="真实姓名长度必须介于 1 和 64 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=1, max=64, message="身份证号长度必须介于 1 和 64 之间")
	public String getIdentifyCardNo() {
		return identifyCardNo;
	}

	public void setIdentifyCardNo(String identifyCardNo) {
		this.identifyCardNo = identifyCardNo;
	}
	
	@Length(min=1, max=64, message="手机号长度必须介于 1 和 64 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=1, max=2, message="邮箱是否验证长度必须介于 1 和 2 之间")
	public String getIsmailvalidate() {
		return ismailvalidate;
	}

	public void setIsmailvalidate(String ismailvalidate) {
		this.ismailvalidate = ismailvalidate;
	}
	
}