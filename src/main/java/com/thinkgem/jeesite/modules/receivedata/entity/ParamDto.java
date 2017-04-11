package com.thinkgem.jeesite.modules.receivedata.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.druid.sql.visitor.functions.Now;

public class ParamDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String segmentId = "1" ;
	
	private String msbId = "4";
	
	private Date beginDate;
	
	private Date endDate;
	
	private String date1;
	
	private String date2;

	public String getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

	public String getMsbId() {
		return msbId;
	}

	public void setMsbId(String msbId) {
		this.msbId = msbId;
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

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}
	
	
	
}
