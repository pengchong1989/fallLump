/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.receivedata.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * receiveDataEntity
 * @author peng
 * @version 2017-02-13
 */
public class Receivedata extends DataEntity<Receivedata> {
	
	private static final long serialVersionUID = 1L;
	private String msbId;		// msb_id
	private String lsbId;		// lsb_id
	private String temperatureMsb;		// 温度传感器字节MSB（温度放大100倍）
	private String temperatureLsb;		// 温度传感器字节LSB（温度放大100倍）
	private String rangingMsb;		// 激光测距传感器字节MSB
	private String rangingLsb;		// 激光测距传感器字节LSB
	private String accelerationXMsb;		// 重力加速度传感器字节X轴加速度MSB
	private String accelerationXLsb;		// 重力加速度传感器字节X轴加速度LSB
	private String accelerationYMsb;		// 重力加速度传感器字节Y轴加速度MSB
	private String accelerationYLsb;		// 重力加速度传感器字节Y轴加速度LSB
	private String accelerationZMsb;		// 重力加速度传感器字节Z轴加速度MSB
	private String accelerationZLsb;		// 重力加速度传感器字节Z轴加速度LSB
	private String linespeedXMsb;		// 重力加速度传感器字节X轴线速度MSB
	private String linespeedXLsb;		// 重力加速度传感器字节X轴线速度LSB
	private String linespeedYMsb;		// 重力加速度传感器字节Y轴线速度MSB
	private String linespeedYLsb;		// 重力加速度传感器字节Y轴线速度LSB
	private String linespeedZMsb;		// 重力加速度传感器字节Z轴线速度MSB
	private String linespeedZLsb;		// 重力加速度传感器字节Z轴线速度LSB
	private String testMsb;		// 测试字节MSB
	private String testLsb;		// 测试字节LSB
	private String gpsLng;		// gps经度
	private String gpsLat;		// gps纬度
	private Date rtcTime;		// rtc时间
	private String alarmStatus;		// 告警状态
	private String segmentIdMsb;		// 段号ID MSB
	private String segmentIdLsb;		// 段号ID LSB
	private String handshakeStatus;		// 握手确认状态
	private String batteryCapacity;		// 电池容量
	private String batteryTemperature;		// 电池温度
	private String batteryStatus;		// 电池状态
	private String amsb;		// amsb
	private String alsb;		// alsb
	private String bmsb;		// bmsb
	private String blsb;		// blsb
	private String cmsb;		// cmsb
	private String clsb;		// clsb
	private String checknum;		// 校验和
	private String signalStrengthMsb;//信号强度msb
	private String signalStrengthLsb;//信号强度lsb
	private String aValue;
	private String bValue;
	
	
	public Receivedata() {
		super();
	}

	public Receivedata(String id){
		super(id);
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
	
	@Length(min=0, max=10, message="温度传感器字节MSB（温度放大100倍）长度必须介于 0 和 10 之间")
	public String getTemperatureMsb() {
		return temperatureMsb;
	}

	public void setTemperatureMsb(String temperatureMsb) {
		this.temperatureMsb = temperatureMsb;
	}
	
	@Length(min=0, max=10, message="温度传感器字节LSB（温度放大100倍）长度必须介于 0 和 10 之间")
	public String getTemperatureLsb() {
		return temperatureLsb;
	}

	public void setTemperatureLsb(String temperatureLsb) {
		this.temperatureLsb = temperatureLsb;
	}
	
	@Length(min=0, max=10, message="激光测距传感器字节MSB长度必须介于 0 和 10 之间")
	public String getRangingMsb() {
		return rangingMsb;
	}

	public void setRangingMsb(String rangingMsb) {
		this.rangingMsb = rangingMsb;
	}
	
	@Length(min=0, max=10, message="激光测距传感器字节LSB长度必须介于 0 和 10 之间")
	public String getRangingLsb() {
		return rangingLsb;
	}

	public void setRangingLsb(String rangingLsb) {
		this.rangingLsb = rangingLsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节X轴加速度MSB长度必须介于 0 和 10 之间")
	public String getAccelerationXMsb() {
		return accelerationXMsb;
	}

	public void setAccelerationXMsb(String accelerationXMsb) {
		this.accelerationXMsb = accelerationXMsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节X轴加速度LSB长度必须介于 0 和 10 之间")
	public String getAccelerationXLsb() {
		return accelerationXLsb;
	}

	public void setAccelerationXLsb(String accelerationXLsb) {
		this.accelerationXLsb = accelerationXLsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节Y轴加速度MSB长度必须介于 0 和 10 之间")
	public String getAccelerationYMsb() {
		return accelerationYMsb;
	}

	public void setAccelerationYMsb(String accelerationYMsb) {
		this.accelerationYMsb = accelerationYMsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节Y轴加速度LSB长度必须介于 0 和 10 之间")
	public String getAccelerationYLsb() {
		return accelerationYLsb;
	}

	public void setAccelerationYLsb(String accelerationYLsb) {
		this.accelerationYLsb = accelerationYLsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节Z轴加速度MSB长度必须介于 0 和 10 之间")
	public String getAccelerationZMsb() {
		return accelerationZMsb;
	}

	public void setAccelerationZMsb(String accelerationZMsb) {
		this.accelerationZMsb = accelerationZMsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节Z轴加速度LSB长度必须介于 0 和 10 之间")
	public String getAccelerationZLsb() {
		return accelerationZLsb;
	}

	public void setAccelerationZLsb(String accelerationZLsb) {
		this.accelerationZLsb = accelerationZLsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节X轴线速度MSB长度必须介于 0 和 10 之间")
	public String getLinespeedXMsb() {
		return linespeedXMsb;
	}

	public void setLinespeedXMsb(String linespeedXMsb) {
		this.linespeedXMsb = linespeedXMsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节X轴线速度LSB长度必须介于 0 和 10 之间")
	public String getLinespeedXLsb() {
		return linespeedXLsb;
	}

	public void setLinespeedXLsb(String linespeedXLsb) {
		this.linespeedXLsb = linespeedXLsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节Y轴线速度MSB长度必须介于 0 和 10 之间")
	public String getLinespeedYMsb() {
		return linespeedYMsb;
	}

	public void setLinespeedYMsb(String linespeedYMsb) {
		this.linespeedYMsb = linespeedYMsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节Y轴线速度LSB长度必须介于 0 和 10 之间")
	public String getLinespeedYLsb() {
		return linespeedYLsb;
	}

	public void setLinespeedYLsb(String linespeedYLsb) {
		this.linespeedYLsb = linespeedYLsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节Z轴线速度MSB长度必须介于 0 和 10 之间")
	public String getLinespeedZMsb() {
		return linespeedZMsb;
	}

	public void setLinespeedZMsb(String linespeedZMsb) {
		this.linespeedZMsb = linespeedZMsb;
	}
	
	@Length(min=0, max=10, message="重力加速度传感器字节Z轴线速度LSB长度必须介于 0 和 10 之间")
	public String getLinespeedZLsb() {
		return linespeedZLsb;
	}

	public void setLinespeedZLsb(String linespeedZLsb) {
		this.linespeedZLsb = linespeedZLsb;
	}
	
	@Length(min=0, max=10, message="测试字节MSB长度必须介于 0 和 10 之间")
	public String getTestMsb() {
		return testMsb;
	}

	public void setTestMsb(String testMsb) {
		this.testMsb = testMsb;
	}
	
	@Length(min=0, max=10, message="测试字节LSB长度必须介于 0 和 10 之间")
	public String getTestLsb() {
		return testLsb;
	}

	public void setTestLsb(String testLsb) {
		this.testLsb = testLsb;
	}
	
	@Length(min=0, max=50, message="gps经度长度必须介于 0 和 50 之间")
	public String getGpsLng() {
		return gpsLng;
	}

	public void setGpsLng(String gpsLng) {
		this.gpsLng = gpsLng;
	}
	
	@Length(min=0, max=50, message="gps纬度长度必须介于 0 和 50 之间")
	public String getGpsLat() {
		return gpsLat;
	}

	public void setGpsLat(String gpsLat) {
		this.gpsLat = gpsLat;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRtcTime() {
		return rtcTime;
	}

	public void setRtcTime(Date rtcTime) {
		this.rtcTime = rtcTime;
	}
	
	@Length(min=0, max=10, message="告警状态长度必须介于 0 和 10 之间")
	public String getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	
	@Length(min=0, max=10, message="段号ID MSB长度必须介于 0 和 10 之间")
	public String getSegmentIdMsb() {
		return segmentIdMsb;
	}

	public void setSegmentIdMsb(String segmentIdMsb) {
		this.segmentIdMsb = segmentIdMsb;
	}
	
	@Length(min=0, max=10, message="段号ID LSB长度必须介于 0 和 10 之间")
	public String getSegmentIdLsb() {
		return segmentIdLsb;
	}

	public void setSegmentIdLsb(String segmentIdLsb) {
		this.segmentIdLsb = segmentIdLsb;
	}
	
	@Length(min=0, max=10, message="握手确认状态长度必须介于 0 和 10 之间")
	public String getHandshakeStatus() {
		return handshakeStatus;
	}

	public void setHandshakeStatus(String handshakeStatus) {
		this.handshakeStatus = handshakeStatus;
	}
	
	@Length(min=0, max=10, message="电池容量长度必须介于 0 和 10 之间")
	public String getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(String batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}
	
	@Length(min=0, max=10, message="电池温度长度必须介于 0 和 10 之间")
	public String getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(String batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}
	
	@Length(min=0, max=10, message="电池状态长度必须介于 0 和 10 之间")
	public String getBatteryStatus() {
		return batteryStatus;
	}

	public void setBatteryStatus(String batteryStatus) {
		this.batteryStatus = batteryStatus;
	}
	
	@Length(min=0, max=10, message="amsb长度必须介于 0 和 10 之间")
	public String getAmsb() {
		return amsb;
	}

	public void setAmsb(String amsb) {
		this.amsb = amsb;
	}
	
	@Length(min=0, max=10, message="alsb长度必须介于 0 和 10 之间")
	public String getAlsb() {
		return alsb;
	}

	public void setAlsb(String alsb) {
		this.alsb = alsb;
	}
	
	@Length(min=0, max=10, message="bmsb长度必须介于 0 和 10 之间")
	public String getBmsb() {
		return bmsb;
	}

	public void setBmsb(String bmsb) {
		this.bmsb = bmsb;
	}
	
	@Length(min=0, max=10, message="blsb长度必须介于 0 和 10 之间")
	public String getBlsb() {
		return blsb;
	}

	public void setBlsb(String blsb) {
		this.blsb = blsb;
	}
	
	@Length(min=0, max=10, message="cmsb长度必须介于 0 和 10 之间")
	public String getCmsb() {
		return cmsb;
	}

	public void setCmsb(String cmsb) {
		this.cmsb = cmsb;
	}
	
	@Length(min=0, max=10, message="clsb长度必须介于 0 和 10 之间")
	public String getClsb() {
		return clsb;
	}

	public void setClsb(String clsb) {
		this.clsb = clsb;
	}
	
	@Length(min=0, max=10, message="校验和长度必须介于 0 和 10 之间")
	public String getChecknum() {
		return checknum;
	}

	public void setChecknum(String checknum) {
		this.checknum = checknum;
	}

	public String getSignalStrengthMsb() {
		return signalStrengthMsb;
	}

	public void setSignalStrengthMsb(String signalStrengthMsb) {
		this.signalStrengthMsb = signalStrengthMsb;
	}

	public String getSignalStrengthLsb() {
		return signalStrengthLsb;
	}

	public void setSignalStrengthLsb(String signalStrengthLsb) {
		this.signalStrengthLsb = signalStrengthLsb;
	}

	public String getaValue() {
		return aValue;
	}

	public void setaValue(String aValue) {
		this.aValue = aValue;
	}

	public String getbValue() {
		return bValue;
	}

	public void setbValue(String bValue) {
		this.bValue = bValue;
	}

	
	
}