/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.receivedata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.receivedata.entity.AlarmData;
import com.thinkgem.jeesite.modules.receivedata.entity.ParamDto;
import com.thinkgem.jeesite.modules.receivedata.entity.Receivedata;
import com.thinkgem.jeesite.modules.receivedata.entity.TypeData;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.nodedata.dao.NodedataDao;
import com.thinkgem.jeesite.modules.nodedata.entity.Nodedata;
import com.thinkgem.jeesite.modules.nodedata.service.NodedataService;
import com.thinkgem.jeesite.modules.receivedata.dao.ReceivedataDao;

/**
 * receiveDataService
 * @author peng
 * @version 2017-02-13
 */
@Service
@Transactional(readOnly = true)
public class ReceivedataService extends CrudService<ReceivedataDao, Receivedata> {

	@Autowired
	private ReceivedataDao receivedataDao;
	
	@Autowired
	private NodedataDao nodedataDao;
	
	public Receivedata get(String id) {
		return super.get(id);
	}
	
	public List<Receivedata> findList(Receivedata receivedata) {
		return super.findList(receivedata);
	}
	
	public Page<Receivedata> findPage(Page<Receivedata> page, Receivedata receivedata) {
		return super.findPage(page, receivedata);
	}
	
	/**
	 * 历史状态
	 * @param page
	 * @param receivedata
	 * @return
	 */
	public Page<Receivedata> findPageList(Page<Receivedata> page, Receivedata receivedata) {
		receivedata.setPage(page);
		page.setOrderBy("rtc_time desc");
		page.setList(receivedataDao.findPageList(receivedata));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(Receivedata receivedata) {
		super.save(receivedata);
	}
	
	@Transactional(readOnly = false)
	public void delete(Receivedata receivedata) {
		super.delete(receivedata);
	}
	
	/**
	 * 告警状态
	 * @param page
	 * @param alarmData
	 * @return
	 */
	public Page<AlarmData> alarmList(Page<AlarmData> page, AlarmData alarmData) {
		alarmData.setPage(page);
		page.setOrderBy("rtc_time desc");
		page.setList(receivedataDao.alarmList(alarmData));
		return page;
	}

	@Transactional(readOnly = false)
	public void saveNew(Receivedata receivedata) {
		receivedataDao.saveNew(receivedata);
	}
	
	@Transactional(readOnly = false)
	public void updateNew(Receivedata receivedata) {
		receivedataDao.updateNew(receivedata);
	}
	
	/**
	 * 当前状态
	 * @param page
	 * @param receivedata
	 * @return
	 */
	public Page<Receivedata> findListNow(Page<Receivedata> page,Receivedata receivedata) {
		receivedata.setPage(page);
		page.setOrderBy("rtc_time desc");
		page.setList(receivedataDao.findListNow(receivedata));
		return page;
	}
	
	/**
	 * 曲线图
	 * @param paramDto
	 * @return
	 */
	public String articleCount(ParamDto paramDto) {
		paramDto.setBeginDate(DateUtils.parseDate(paramDto.getDate1()));
		paramDto.setEndDate(DateUtils.parseDate(paramDto.getDate2()));
		List<Map<String, Object>> list = receivedataDao.articleCount(paramDto);
		Map<String, Object> map = new HashMap<String, Object>();
		String[] rtc_time = new String[list.size()];
		String[] temperature_msb = new String[list.size()];
		String[] ranging_msb = new String[list.size()];
		String[] aValue = new String[list.size()];
		String[] bValue = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			rtc_time[i] = DateUtils.formatDate((Date) list.get(i).get("rtc_time"),"yyyy-MM-dd HH:mm");
			temperature_msb[i] =  list.get(i).get("temperature_msb").toString();
			ranging_msb[i] = list.get(i).get("ranging_msb").toString();
			aValue[i] = list.get(i).get("aValue").toString();
			bValue[i] = list.get(i).get("bValue").toString();
		}
		map.put("rtc_time", rtc_time);
		map.put("temperature_msb", temperature_msb);
		map.put("ranging_msb", ranging_msb);
		map.put("aValue", aValue);
		map.put("bValue", bValue);
		return JSON.toJSONString(map);
	}
	
	/**
	 * 统计图
	 * @param paramDto
	 * @return
	 */
	public String statisticsCount() {
		List<Map<String, Object>> list = receivedataDao.statisticsCount();
		int online = 0;
		int outline = 0;
		int up = 0;
		int down = 0;
		int normal = 0;
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> map = list.get(j);
			Date d = (Date) map.get("rtc_time");
			long l = System.currentTimeMillis()-d.getTime();
			if(l>Integer.parseInt(map.get("sensor_time1").toString())*1000){
				outline +=1;
			}else{
				online +=1;
			}
			String status = Integer.toBinaryString((Integer)map.get("alarm_status"));
			while(status.length()<8){
				status += "0";
			}
			if(status.substring(7, 8).equals("0")){
				up +=1;
			}else if(status.substring(6, 7).equals("0")){
				down +=1;
			}else{
				normal +=1;
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<TypeData> list1 = new ArrayList<TypeData>();
		TypeData typeData = new TypeData();
		typeData.setName("在线");
		typeData.setValue(online+"");
		TypeData typeData2 = new TypeData();
		typeData2.setName("离线");
		typeData2.setValue(outline+"");
		list1.add(typeData);
		list1.add(typeData2);
		
		List<TypeData> list2 = new ArrayList<TypeData>();
		TypeData typeData3 = new TypeData();
		typeData3.setName("上限位警报");
		typeData3.setValue(up+"");
		TypeData typeData4 = new TypeData();
		typeData4.setName("下限位警报");
		typeData4.setValue(down+"");
		TypeData typeData5 = new TypeData();
		typeData5.setName("正常");
		typeData5.setValue(down+"");
		list2.add(typeData3);
		list2.add(typeData4);
		list2.add(typeData5);
		map.put("list1", list1);
		map.put("list2", list2);
		return JSON.toJSONString(map);
	}

	/**
	 * 节点在线状态
	 * @param paramDto
	 * @return
	 */
	public String nodeTopology(ParamDto paramDto) {
		List<Map<String, Object>> list = receivedataDao.statisticsCount();
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> map = list.get(j);
			Date d = (Date) map.get("rtc_time");
			long l = System.currentTimeMillis()-d.getTime();
			if(l>(Integer.parseInt(map.get("sensor_time1").toString())*1000*60+2*60*100)){
				map.put("isline", 0);
			}else{
				map.put("isline", 1);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return JSON.toJSONString(map);
	}
}