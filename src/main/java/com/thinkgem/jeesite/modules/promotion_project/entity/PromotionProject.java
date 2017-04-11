/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.promotion_project.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 招商项目Entity
 * @author pengc
 * @version 2016-06-16
 */
public class PromotionProject extends DataEntity<PromotionProject> {
	
	private static final long serialVersionUID = 1L;
	private String projectName;		// 项目名称
	private String companyName;		// 公司名称
	private Date releaseTime;		// 发布时间
	private String status;		// 出版单位
	private String operatingUnit;		// 运营单位
	private String province;		// 省份
	private String referenceNumber;		// 文号
	private String gameType;		// 游戏类型
	
	public PromotionProject() {
		super();
	}

	public PromotionProject(String id){
		super(id);
	}

	@Length(min=0, max=64, message="项目名称长度必须介于 0 和 64 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=0, max=64, message="公司名称长度必须介于 0 和 64 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="发布时间不能为空")
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	@Length(min=0, max=64, message="出版单位长度必须介于 0 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=64, message="运营单位长度必须介于 0 和 64 之间")
	public String getOperatingUnit() {
		return operatingUnit;
	}

	public void setOperatingUnit(String operatingUnit) {
		this.operatingUnit = operatingUnit;
	}
	
	@Length(min=0, max=64, message="省份长度必须介于 0 和 64 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=64, message="文号长度必须介于 0 和 64 之间")
	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	@Length(min=0, max=64, message="游戏类型长度必须介于 0 和 64 之间")
	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
}