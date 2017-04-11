/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.nodedata.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.nodedata.entity.Nodedata;

/**
 * nodeDataDAO接口
 * @author peng
 * @version 2017-02-13
 */
@MyBatisDao
public interface NodedataDao extends CrudDao<Nodedata> {
	
}