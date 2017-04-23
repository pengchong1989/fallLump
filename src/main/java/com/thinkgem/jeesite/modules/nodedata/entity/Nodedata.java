/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.nodedata.entity;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * nodeDataEntity
 * @author peng
 * @version 2017-02-13
 */
public class Nodedata extends DataEntity<Nodedata> {
	
	private static final long serialVersionUID = 1L;
	private Integer testmsb=0;		// 激光测距传感器  
	private String testlsb;		// testlsb
	private String command;		// 命令字节
	private String sensorTime1="30";		// 传感器时间1
	private String sensorTime2Msb="70";		// sensor_time2_msb
	private String sensorTime2Lsb;		// sensor_time2_lsb
	private String segmentIdMsb;		// 段 ID MSB
	private String segmentIdLsb;		// 段 ID LSB
	private String msbId;		// msb_id
	private String lsbId;		// lsb_id
	private String upLimitMsb;		// 上限位距离MSB
	private String upLimitLsb;		// 上限位距离LSB
	private String lowerLimitMsb ="5000";		// 下限位距离MSB
	private String lowerLimitLsb ="50";		// 下限位距离LSB
	private String malfunctionStatus;		// 故障状态
	private String restart;		// 重启节点
	private String checknum;		// 校验和
	private String nodeId;//节点id
	private Integer command_restart;//命令字节中的芯片重启：重启（0）/正常工作（1）
	private Integer command_mpu_status;//激光测距、温度、MPU-6050传感器：关闭（0）/正常工作（1）
	private Integer command_gprs_status;//GPRS传感器：关闭（0）/正常工作（1）
	private Integer command_gps_status;//GPS传感器：关闭（0）/正常工作（1）
	private Integer command_other_status;//其它温度传感器数据控制：关闭（0）/正常传输（1）
	private Integer command_sleep;//休眠：休眠（0）/唤醒（1）
	private Integer command_update_time;//非使能（0）/使能（1）
	private Integer command_update_time2;//修改GPRS传感器定时器2时间：非使能（0）/使能（1）
	private Integer malfunction_confirm;//用户故障确认（1）/默认（0）
	private Integer malfunction_exclude;//用户故障排除，恢复设备功能（1）/默认（0）
	private Integer malfunction_maintenance;//正在检修（1）/默认（0）
	private Integer malfunction_restarting;//手动重启（1）/默认0
	private Integer malfunction_initialize;//初始化配置标志（1）/默认0
	private Integer a0;
	private Integer b0;
	private Integer c0;
	
	public Nodedata() {
		super();
	}

	public Nodedata(String id){
		super(id);
	}

	public Integer getTestmsb() {
		return testmsb;
	}

	public void setTestmsb(Integer testmsb) {
		this.testmsb = testmsb;
	}
	
	@Length(min=0, max=10, message="testlsb长度必须介于 0 和 10 之间")
	public String getTestlsb() {
		return testlsb;
	}

	public void setTestlsb(String testlsb) {
		this.testlsb = testlsb;
	}
	
	@Length(min=0, max=10, message="命令字节长度必须介于 0 和 10 之间")
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	@Length(min=0, max=10, message="传感器时间1长度必须介于 0 和 10 之间")
	public String getSensorTime1() {
		return sensorTime1;
	}

	public void setSensorTime1(String sensorTime1) {
		this.sensorTime1 = sensorTime1;
	}
	
	@Length(min=0, max=10, message="sensor_time2_msb长度必须介于 0 和 10 之间")
	public String getSensorTime2Msb() {
		return sensorTime2Msb;
	}

	public void setSensorTime2Msb(String sensorTime2Msb) {
		this.sensorTime2Msb = sensorTime2Msb;
	}
	
	@Length(min=0, max=10, message="sensor_time2_lsb长度必须介于 0 和 10 之间")
	public String getSensorTime2Lsb() {
		return sensorTime2Lsb;
	}

	public void setSensorTime2Lsb(String sensorTime2Lsb) {
		this.sensorTime2Lsb = sensorTime2Lsb;
	}
	
	@Length(min=0, max=10, message="段 ID MSB长度必须介于 0 和 10 之间")
	public String getSegmentIdMsb() {
		return segmentIdMsb;
	}

	public void setSegmentIdMsb(String segmentIdMsb) {
		this.segmentIdMsb = segmentIdMsb;
	}
	
	@Length(min=0, max=10, message="段 ID LSB长度必须介于 0 和 10 之间")
	public String getSegmentIdLsb() {
		return segmentIdLsb;
	}

	public void setSegmentIdLsb(String segmentIdLsb) {
		this.segmentIdLsb = segmentIdLsb;
	}
	
	@Length(min=0, max=10, message="msb_id长度必须介于 0 和 10 之间")
	public String getMsbId() {
		return msbId;
	}

	public void setMsbId(String msbId) {
		this.msbId = msbId;
	}
	
	@Length(min=0, max=10, message="lsb_id长度必须介于 0 和 10 之间")
	public String getLsbId() {
		return lsbId;
	}

	public void setLsbId(String lsbId) {
		this.lsbId = lsbId;
	}
	
	@Length(min=0, max=10, message="上限位距离MSB长度必须介于 0 和 10 之间")
	public String getUpLimitMsb() {
		return upLimitMsb;
	}

	public void setUpLimitMsb(String upLimitMsb) {
		this.upLimitMsb = upLimitMsb;
	}
	
	@Length(min=0, max=10, message="上限位距离LSB长度必须介于 0 和 10 之间")
	public String getUpLimitLsb() {
		return upLimitLsb;
	}

	public void setUpLimitLsb(String upLimitLsb) {
		this.upLimitLsb = upLimitLsb;
	}
	
	@Length(min=0, max=10, message="下限位距离MSB长度必须介于 0 和 10 之间")
	public String getLowerLimitMsb() {
		return lowerLimitMsb;
	}

	public void setLowerLimitMsb(String lowerLimitMsb) {
		this.lowerLimitMsb = lowerLimitMsb;
	}
	
	@Length(min=0, max=10, message="下限位距离LSB长度必须介于 0 和 10 之间")
	public String getLowerLimitLsb() {
		return lowerLimitLsb;
	}

	public void setLowerLimitLsb(String lowerLimitLsb) {
		this.lowerLimitLsb = lowerLimitLsb;
	}
	
	@Length(min=0, max=10, message="故障状态长度必须介于 0 和 10 之间")
	public String getMalfunctionStatus() {
		return malfunctionStatus;
	}

	public void setMalfunctionStatus(String malfunctionStatus) {
		this.malfunctionStatus = malfunctionStatus;
	}
	
	@Length(min=0, max=10, message="重启节点长度必须介于 0 和 10 之间")
	public String getRestart() {
		return restart;
	}

	public void setRestart(String restart) {
		this.restart = restart;
	}
	
	@Length(min=0, max=30, message="校验和长度必须介于 0 和 30 之间")
	public String getChecknum() {
		return checknum;
	}

	public void setChecknum(String checknum) {
		this.checknum = checknum;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getCommand_restart() {
		return command_restart;
	}

	public void setCommand_restart(Integer command_restart) {
		this.command_restart = command_restart;
	}

	public Integer getCommand_mpu_status() {
		return command_mpu_status;
	}

	public void setCommand_mpu_status(Integer command_mpu_status) {
		this.command_mpu_status = command_mpu_status;
	}

	public Integer getCommand_gprs_status() {
		return command_gprs_status;
	}

	public void setCommand_gprs_status(Integer command_gprs_status) {
		this.command_gprs_status = command_gprs_status;
	}

	public Integer getCommand_gps_status() {
		return command_gps_status;
	}

	public void setCommand_gps_status(Integer command_gps_status) {
		this.command_gps_status = command_gps_status;
	}

	public Integer getCommand_other_status() {
		return command_other_status;
	}

	public void setCommand_other_status(Integer command_other_status) {
		this.command_other_status = command_other_status;
	}

	public Integer getCommand_sleep() {
		return command_sleep;
	}

	public void setCommand_sleep(Integer command_sleep) {
		this.command_sleep = command_sleep;
	}

	public Integer getCommand_update_time() {
		return command_update_time;
	}

	public void setCommand_update_time(Integer command_update_time) {
		this.command_update_time = command_update_time;
	}

	public Integer getCommand_update_time2() {
		return command_update_time2;
	}

	public void setCommand_update_time2(Integer command_update_time2) {
		this.command_update_time2 = command_update_time2;
	}

	public Integer getMalfunction_confirm() {
		return malfunction_confirm;
	}

	public void setMalfunction_confirm(Integer malfunction_confirm) {
		this.malfunction_confirm = malfunction_confirm;
	}

	public Integer getMalfunction_exclude() {
		return malfunction_exclude;
	}

	public void setMalfunction_exclude(Integer malfunction_exclude) {
		this.malfunction_exclude = malfunction_exclude;
	}

	public Integer getMalfunction_maintenance() {
		return malfunction_maintenance;
	}

	public void setMalfunction_maintenance(Integer malfunction_maintenance) {
		this.malfunction_maintenance = malfunction_maintenance;
	}

	public Integer getMalfunction_restarting() {
		return malfunction_restarting;
	}

	public void setMalfunction_restarting(Integer malfunction_restarting) {
		this.malfunction_restarting = malfunction_restarting;
	}

	public Integer getMalfunction_initialize() {
		return malfunction_initialize;
	}

	public void setMalfunction_initialize(Integer malfunction_initialize) {
		this.malfunction_initialize = malfunction_initialize;
	}

	public Integer getA0() {
		return a0;
	}

	public void setA0(Integer a0) {
		this.a0 = a0;
	}

	public Integer getB0() {
		return b0;
	}

	public void setB0(Integer b0) {
		this.b0 = b0;
	}

	public Integer getC0() {
		return c0;
	}

	public void setC0(Integer c0) {
		this.c0 = c0;
	}

	public void setTestmsb(int testmsb) {
		this.testmsb = testmsb;
	}
	
	
}