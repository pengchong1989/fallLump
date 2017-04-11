package com.thinkgem.jeesite.modules.netNode;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.constants.ZTConstants;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.NetUtil;
import com.thinkgem.jeesite.modules.receivedata.entity.Receivedata;
import com.thinkgem.jeesite.modules.receivedata.service.ReceivedataService;

@Component("messageDelegateListener")
public class ReadMessageListener {
	
	protected static final Log logger = LogFactory.getLog(ReadMessageListener.class);
	
	@Resource
	private ReceivedataService receivedataService;
	
	
    public void handleMessage(Object message){
    	byte[] bs = (byte[]) message;
    	System.out.println("messageDelegateListener"+DateUtils.getDate());
    	NetUtil.print16String(bs);
    	for (int i = 0; i < bs.length/60; i++) {
			byte[] commond = new byte[60];
			for (int j = 0; j < commond.length; j++) {
				commond[j] = bs[j+i*60];
			}
			receivedataService.save(this.getReceivedata(commond));
			if(i == (bs.length/60-1)){
				receivedataService.updateNew(this.getReceivedata(commond));
			}
    	}
    }
    
    private int getInt(byte b1,byte b2){
		byte[] bs = new byte[4];
		bs[0] = 0;
		bs[1] = 0;
		bs[2] = b1;
		bs[3] = b2;
		return NetUtil.bytesToInt(bs);
	}
	
    private String getSpeed(byte b1,byte b2){
    	String speed = "";
    	String str1 = Integer.toBinaryString(NetUtil.bytesToInt(b1));
    	if(str1.length()<8){
    		speed = getInt(b1, b2)+"";
    	}else{
    		speed = "-"+(Integer.parseInt(str1.substring(1), 2)*256+NetUtil.bytesToInt(b2));
    	}
    	return speed;
    }
    
	private Receivedata getReceivedata(byte[] bs){
		Receivedata receivedata = new Receivedata();
		receivedata.setMsbId(getInt(bs[2], bs[3])+"");
		receivedata.setTemperatureMsb(getInt(bs[4], bs[5])+"");
		receivedata.setRangingMsb(getInt(bs[6], bs[7])+"");
		
		receivedata.setAccelerationXMsb(getSpeed(bs[8], bs[9]));
		receivedata.setAccelerationYMsb(getSpeed(bs[10], bs[11]));
		receivedata.setAccelerationZMsb(getSpeed(bs[12], bs[13]));
		receivedata.setLinespeedXMsb(getSpeed(bs[14], bs[15]));
		receivedata.setLinespeedYMsb(getSpeed(bs[16], bs[17]));
		receivedata.setLinespeedZMsb(getSpeed(bs[18], bs[19]));
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
		receivedata.setSegmentIdMsb(getInt(bs[40], bs[41])+"");
		receivedata.setSignalStrengthMsb(getInt(bs[42], bs[43])+"");
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
		Map<String,Object> map = (Map<String, Object>) JsonMapper.fromJsonString(JedisUtils.get(ZTConstants.jedisKey.send_data_string+receivedata.getSegmentIdMsb()+"_"+receivedata.getMsbId()), Map.class);
		receivedata.setaValue(((Integer)map.get("a0")-(Integer)map.get("b0")+Integer.parseInt(receivedata.getRangingMsb()))+"");
		receivedata.setbValue(((Integer)map.get("b0")+(Integer)map.get("c0")-Integer.parseInt(receivedata.getRangingMsb()))+"");
		return receivedata;
	}
}