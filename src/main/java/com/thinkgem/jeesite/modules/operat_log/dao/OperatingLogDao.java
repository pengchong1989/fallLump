/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.operat_log.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.operat_log.entity.OperatingLog;

/**
 * 操作日志DAO接口
 * @author admin
 * @version 2017-04-23
 */
@MyBatisDao
public interface OperatingLogDao extends CrudDao<OperatingLog> {
	
}