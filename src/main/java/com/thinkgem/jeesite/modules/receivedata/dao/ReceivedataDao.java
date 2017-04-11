/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.receivedata.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.receivedata.entity.AlarmData;
import com.thinkgem.jeesite.modules.receivedata.entity.ParamDto;
import com.thinkgem.jeesite.modules.receivedata.entity.Receivedata;

/**
 * receiveDataDAO接口
 * @author peng
 * @version 2017-02-13
 */
@MyBatisDao
public interface ReceivedataDao extends CrudDao<Receivedata> {

	List<AlarmData> alarmList(AlarmData alarmData);

	List<Receivedata> findPageList(Receivedata receivedata);

	void saveNew(Receivedata receivedata);

	List<Receivedata> findListNow(Receivedata receivedata);

	List<Map<String, Object>> articleCount(@Param("paramDto")ParamDto paramDto);

	List<Map<String, Object>> statisticsCount();

	void updateNew(Receivedata receivedata);
	
}