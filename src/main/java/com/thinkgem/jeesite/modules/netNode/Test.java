package com.thinkgem.jeesite.modules.netNode;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.NetUtil;
import com.thinkgem.jeesite.modules.receivedata.entity.Receivedata;

public class Test {
    public static void main(String[] args) {
		String str = "EB 90 00 04 00 D5 00 15 00 EE FF 56 3F AA FF CF "+
					 "00 21 00 00 00 00 00 00 00 00 00 00 00 00 00 00 "+
					 "0E 41 11 03 07 0A 2E 1F 00 01 00 0D FF FF 00 FF "+
					 "00 60 00 FF 00 00 FF FF FF FF FF 2F";
		byte[] bs = new byte[str.split(" ").length];
		for (int i = 0; i < bs.length; i++) {
			bs[i] = (byte) Integer.parseInt(str.split(" ")[i], 16);
		}
		
		Test test = new Test();
		Receivedata receivedata = test.getReceivedata(bs);
		System.out.println(receivedata.getAccelerationXLsb());
	}
    
    
    private int getInt(byte b1,byte b2){
		byte[] bs = new byte[4];
		bs[0] = 0;
		bs[1] = 0;
		bs[2] = b1;
		bs[3] = b2;
		return NetUtil.bytesToInt(bs);
	}
	

    
	private Receivedata getReceivedata(byte[] bs){
		Receivedata receivedata = new Receivedata();
		receivedata.setMsbId(getInt(bs[2], bs[3])+"");
		receivedata.setTemperatureMsb(getInt(bs[4], bs[5])+"");
		receivedata.setRangingMsb(getInt(bs[6], bs[7])+"");
		
		receivedata.setAccelerationXMsb(getInt(bs[8], bs[9])+"");
		receivedata.setAccelerationYMsb(getInt(bs[10], bs[11])+"");
		receivedata.setAccelerationZMsb(getInt(bs[12], bs[13])+"");
		receivedata.setLinespeedXMsb(getInt(bs[14], bs[15])+"");
		receivedata.setLinespeedYMsb(getInt(bs[16], bs[17])+"");
		receivedata.setLinespeedZMsb(getInt(bs[18], bs[19])+"");
		receivedata.setTestMsb(getInt(bs[20], bs[21])+"");
		byte[] lng = new byte[4];
		lng[0] = bs[22];
		lng[1] = bs[23];
		lng[2] = bs[24];
		lng[3] = bs[25];
		String str1 = NetUtil.bytesToInt(lng)+"";
		int lngIndex = NetUtil.bytesToInt(bs[26]);
		receivedata.setGpsLng(str1.subSequence(0, str1.length()-lngIndex)+"."+str1.substring(str1.length()-lngIndex));
		
		byte[] lat = new byte[4];
		lat[0] = bs[27];
		lat[1] = bs[28];
		lat[2] = bs[29];
		lat[3] = bs[30];
		String str2 = NetUtil.bytesToInt(lat)+"";
		int latIndex = NetUtil.bytesToInt(bs[31]);
		receivedata.setGpsLat(str2.subSequence(0, str2.length()-latIndex)+"."+str2.substring(str2.length()-latIndex));
		
		//32 33空余字节
		receivedata.setRtcTime(DateUtils.parseDate("20"+NetUtil.bytesToInt(bs[34])+"-"+NetUtil.bytesToInt(bs[35])+"-"+NetUtil.bytesToInt(bs[36])
								+" "+NetUtil.bytesToInt(bs[37])+":"+NetUtil.bytesToInt(bs[38])));
		
		
		//告警状态少第7位
		receivedata.setAlarmStatus(NetUtil.bytesToInt(bs[39])+"");
		receivedata.setSegmentIdMsb(NetUtil.bytesToInt(bs[40])+"");
		receivedata.setSegmentIdLsb(NetUtil.bytesToInt(bs[41])+"");
		receivedata.setSignalStrengthMsb(NetUtil.bytesToInt(bs[42])+"");
		receivedata.setSignalStrengthLsb(NetUtil.bytesToInt(bs[43])+"");
		//空余4字节
		receivedata.setHandshakeStatus(NetUtil.bytesToInt(bs[48])+"");
		receivedata.setBatteryCapacity(NetUtil.bytesToInt(bs[49])+"");
		receivedata.setBatteryTemperature(NetUtil.bytesToInt(bs[50])+"");
		receivedata.setBatteryStatus(NetUtil.bytesToInt(bs[51])+"");
		receivedata.setAmsb(NetUtil.bytesToInt(bs[52])+"");
		receivedata.setAlsb(NetUtil.bytesToInt(bs[53])+"");
		receivedata.setBmsb(NetUtil.bytesToInt(bs[54])+"");
		receivedata.setBlsb(NetUtil.bytesToInt(bs[55])+"");
		receivedata.setCmsb(NetUtil.bytesToInt(bs[56])+"");
		receivedata.setClsb(NetUtil.bytesToInt(bs[57])+"");
		return receivedata;
	}
}
