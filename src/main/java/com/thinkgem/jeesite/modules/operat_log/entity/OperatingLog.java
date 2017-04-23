/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.operat_log.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 操作日志Entity
 * @author admin
 * @version 2017-04-23
 */
public class OperatingLog extends DataEntity<OperatingLog> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 配置名称
	private String userid;		// 操作人
	private String params;		// 参数值
	private String title;		// 参数标题
	private Date beginDate;		// 开始日期
	private Date endDate;		// 结束日期
	
	public OperatingLog() {
		super();
	}

	public OperatingLog(String id){
		super(id);
	}

	@Length(min=0, max=2, message="配置名城长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=50, message="操作人长度必须介于 1 和 50 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=1, max=50, message="参数值长度必须介于 1 和 50 之间")
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	@Length(min=1, max=50, message="参数标题长度必须介于 1 和 50 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}