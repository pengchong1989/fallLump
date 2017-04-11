/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.user_additional.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户附加信息Entity
 * @author pengc
 * @version 2016-06-16
 */
public class UserAdditional extends DataEntity<UserAdditional> {
	
	private static final long serialVersionUID = 1L;
	private String user;		// 认领人
	private String name;		// 名称
	private Date releaseTime;		// 认领时间
	private String type;		// 分类
	private String information;		// 证照信息
	private String auditStatus;		// 审核状态
	
	public UserAdditional() {
		super();
	}

	public UserAdditional(String id){
		super(id);
	}

	@Length(min=0, max=64, message="认领人长度必须介于 0 和 64 之间")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="认领时间不能为空")
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	@Length(min=0, max=3, message="分类长度必须介于 0 和 3 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="证照信息长度必须介于 0 和 64 之间")
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	@Length(min=0, max=3, message="审核状态长度必须介于 0 和 3 之间")
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
}