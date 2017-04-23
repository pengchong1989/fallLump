/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.operat_log.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.operat_log.entity.OperatingLog;
import com.thinkgem.jeesite.modules.sys.entity.Log;
import com.thinkgem.jeesite.modules.operat_log.dao.OperatingLogDao;

/**
 * 操作日志Service
 * @author admin
 * @version 2017-04-23
 */
@Service
@Transactional(readOnly = true)
public class OperatingLogService extends CrudService<OperatingLogDao, OperatingLog> {

	public OperatingLog get(String id) {
		return super.get(id);
	}
	
	public List<OperatingLog> findList(OperatingLog operatingLog) {
		return super.findList(operatingLog);
	}
	
	public Page<OperatingLog> findPage(Page<OperatingLog> page, OperatingLog operatingLog) {
		
		// 设置默认时间范围，默认当前月
		if (operatingLog.getBeginDate() == null){
			operatingLog.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (operatingLog.getEndDate() == null){
			operatingLog.setEndDate(DateUtils.addMonths(operatingLog.getBeginDate(), 1));
		}
		return super.findPage(page, operatingLog);
		
	}
	
	@Transactional(readOnly = false)
	public void save(OperatingLog operatingLog) {
		super.save(operatingLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(OperatingLog operatingLog) {
		super.delete(operatingLog);
	}
	
}