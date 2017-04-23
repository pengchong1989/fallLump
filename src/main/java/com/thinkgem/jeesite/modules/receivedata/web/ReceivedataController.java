/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.receivedata.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.constants.ZTConstants;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.NetUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.receivedata.entity.AlarmData;
import com.thinkgem.jeesite.modules.receivedata.entity.ParamDto;
import com.thinkgem.jeesite.modules.receivedata.entity.Receivedata;
import com.thinkgem.jeesite.modules.receivedata.service.ReceivedataService;

/**
 * receiveDataController
 * @author peng
 * @version 2017-02-13
 */
@Controller
@RequestMapping(value = "${adminPath}/receivedata/receivedata")
public class ReceivedataController extends BaseController {

	@Autowired
	private ReceivedataService receivedataService;
	
	@ModelAttribute
	public Receivedata get(@RequestParam(required=false) String id) {
		Receivedata entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = receivedataService.get(id);
		}
		if (entity == null){
			entity = new Receivedata();
		}
		return entity;
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
		return receivedata;
	}
	
	@RequiresPermissions("receivedata:receivedata:view")
	@RequestMapping(value = {"list", ""})
	public String list(Receivedata receivedata, HttpServletRequest request, HttpServletResponse response, Model model) {
		String str = "EB 90 00 05 01 20 00 18 00 00 00 00 00 00 00 00 "+ 
"00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 "+  
"0F 03 11 03 03 10 0F 1F 00 01 00 0E FF FF 00 FF "+  
"00 60 00 FF 00 00 FF FF FF FF FF 0B";
		String[] strs = str.split(" ");
		byte[] bs = new byte[strs.length];
		for (int i = 1; i < bs.length; i++) {
			bs[i] = (byte) Integer.parseInt(strs[i],16);
		}
		/*for (int i = 0; i < 5000000; i++) {
			
			Receivedata receive = getReceivedata(bs);
			receive.setMsbId(i%500+"");
			receivedataService.save(receive);
		}*/
		Page<Receivedata> page = receivedataService.findPageList(new Page<Receivedata>(request, response), receivedata); 
		for (int i = 0; i < page.getList().size(); i++) {
			Receivedata receivedata2 = page.getList().get(i);
			Double d = Integer.parseInt(receivedata2.getTemperatureMsb())/10.0;
			receivedata2.setTemperatureMsb(d+"");
			receivedata2.setRangingMsb(Integer.parseInt(receivedata2.getRangingMsb())*5+"");
			Map<String,Object> map = (Map<String, Object>) JsonMapper.fromJsonString(JedisUtils.get(ZTConstants.jedisKey.send_data_string+receivedata2.getSegmentIdMsb()+"_"+receivedata2.getMsbId()), Map.class);
			receivedata2.setaValue(((Integer)map.get("a0")-(Integer)map.get("c0")+Integer.parseInt(receivedata2.getRangingMsb()))+"");
			receivedata2.setbValue(((Integer)map.get("b0")+(Integer)map.get("c0")-Integer.parseInt(receivedata2.getRangingMsb()))+"");
		}
		model.addAttribute("page", page);
		return "modules/receivedata/receivedataList";
	}

	@RequiresPermissions("receivedata:receivedata:view")
	@RequestMapping(value = "form")
	public String form(Receivedata receivedata, Model model) {
		model.addAttribute("receivedata", receivedata);
		return "modules/receivedata/receivedataForm";
	}

	@RequiresPermissions("receivedata:receivedata:edit")
	@RequestMapping(value = "save")
	public String save(Receivedata receivedata, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, receivedata)){
			return form(receivedata, model);
		}
		receivedataService.save(receivedata);
		addMessage(redirectAttributes, "保存receiveData成功");
		return "redirect:"+Global.getAdminPath()+"/receivedata/receivedata/?repage";
	}
	
	
	
	@RequiresPermissions("receivedata:receivedata:edit")
	@RequestMapping(value = "delete")
	public String delete(Receivedata receivedata, RedirectAttributes redirectAttributes) {
		receivedataService.delete(receivedata);
		addMessage(redirectAttributes, "删除receiveData成功");
		return "redirect:"+Global.getAdminPath()+"/receivedata/receivedata/?repage";
	}
	
	@RequiresPermissions("receivedata:receivedata:view")
	@RequestMapping(value = "/alarmList")
	public String alarmList(AlarmData alarmData, HttpServletRequest request, HttpServletResponse response, Model model) {
		byte[] bs1 = (byte[]) JedisUtils.getObject("send_data"+1+"_"+4);
		NetUtil.print16String(bs1);
		Page<AlarmData> page = receivedataService.alarmList(new Page<AlarmData>(request, response), alarmData);
		for (int i = 0; i < page.getList().size(); i++) {
			AlarmData data = page.getList().get(i);
			String status = Integer.toBinaryString(Integer.parseInt(data.getAlarmStatus()));
			while(status.length()<8){
				status += "0";
			}
			System.out.println("sss::"+status);
			data.setUpAlarm(status.substring(0, 1).equals("1")?"正常工作":"告警");
			data.setLowAlarm(status.substring(1, 2).equals("1")?"正常工作":"告警");
			data.setHighTemperatureAlarm(status.substring(2, 3).equals("1")?"正常工作":"告警");
			data.setLowTemperatureAlarm(status.substring(3, 4).equals("1")?"正常工作":"告警");
			data.setTiltAlarm(status.substring(4, 5).equals("1")?"正常工作":"告警");
		}
		model.addAttribute("page", page);
		return "modules/receivedata/dataAlarmList";
	}
	
	@RequestMapping(value = {"/listNew"})
	public String listNew(Receivedata receivedata, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Receivedata> page = receivedataService.findListNow(new Page<Receivedata>(request, response), receivedata); 
		for (int i = 0; i < page.getList().size(); i++) {
			Receivedata receivedata2 = page.getList().get(i);
			Double d = Integer.parseInt(receivedata2.getTemperatureMsb())/10.0;
			receivedata2.setTemperatureMsb(d+"");
			receivedata2.setRangingMsb(Integer.parseInt(receivedata2.getRangingMsb())*5+"");
			Map<String,Object> map = (Map<String, Object>) JsonMapper.fromJsonString(JedisUtils.get(ZTConstants.jedisKey.send_data_string+receivedata2.getSegmentIdMsb()+"_"+receivedata2.getMsbId()), Map.class);
		}
		model.addAttribute("page", page);
		return "modules/receivedata/receivedataList";
	}
	
	@RequestMapping(value = "/abValue")
	public String abValue(ParamDto paramDto, Model model) {
		model.addAttribute("paramDto", paramDto);
		return "modules/receivedata/abValue";
	}
	
	@RequestMapping(value = "/ranging")
	public String ranging(ParamDto paramDto, Model model) {
		model.addAttribute("paramDto", paramDto);
		return "modules/receivedata/ranging";
	}
	
	/**
	 * 曲线图
	 * @param paramDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/articleCount")
	@ResponseBody
	public String articleCount(@RequestBody ParamDto paramDto, Model model) {
		if(paramDto == null){
			paramDto = new ParamDto();
		}
		return receivedataService.articleCount(paramDto);
	}
	
	
	/**
	 * 统计图
	 * @param paramDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/statisticsCount")
	public String statisticsCount(ParamDto paramDto, Model model) {
		model.addAttribute("paramDto", paramDto);
		return "modules/receivedata/alarmCount";
	}
	
	@RequestMapping(value = "/alarmCount")
	@ResponseBody
	public String count( Model model) {
		return receivedataService.statisticsCount();
	}
	
	@RequestMapping(value = "/topology")
	public String topology(Model model) {
		return "modules/topology/topology";
	}
	
	@RequestMapping(value = "/nodeTopology")
	@ResponseBody
	public String nodeTopology(ParamDto paramDto){
		return receivedataService.nodeTopology(paramDto);
	}
	
}