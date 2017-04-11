package com.thinkgem.jeesite.modules.receivedata.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class AlarmData extends DataEntity<AlarmData>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7274526129285363395L;
	
	private String segmentId;//段id
	
	private String msbId;//设备id
	
	private String alarmStatus;//告警状态
	
	private String upAlarm;//高限位告警
	
	private String lowAlarm;//低限位告警
	
	private String highTemperatureAlarm;//高温告警
	
	private String lowTemperatureAlarm;//低温告警
	
	private String tiltAlarm;//倾斜告警

	private Date alarmDate;//采集时间
	
	public String getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}


	public String getUpAlarm() {
		return upAlarm;
	}

	public void setUpAlarm(String upAlarm) {
		this.upAlarm = upAlarm;
	}

	public String getLowAlarm() {
		return lowAlarm;
	}

	public void setLowAlarm(String lowAlarm) {
		this.lowAlarm = lowAlarm;
	}

	public String getHighTemperatureAlarm() {
		return highTemperatureAlarm;
	}

	public void setHighTemperatureAlarm(String highTemperatureAlarm) {
		this.highTemperatureAlarm = highTemperatureAlarm;
	}

	public String getLowTemperatureAlarm() {
		return lowTemperatureAlarm;
	}

	public void setLowTemperatureAlarm(String lowTemperatureAlarm) {
		this.lowTemperatureAlarm = lowTemperatureAlarm;
	}

	public String getTiltAlarm() {
		return tiltAlarm;
	}

	public void setTiltAlarm(String tiltAlarm) {
		this.tiltAlarm = tiltAlarm;
	}

	public String getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	public String getMsbId() {
		return msbId;
	}

	public void setMsbId(String msbId) {
		this.msbId = msbId;
	}

	
}
