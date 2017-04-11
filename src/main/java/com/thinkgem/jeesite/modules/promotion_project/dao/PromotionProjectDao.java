/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.promotion_project.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.promotion_project.entity.PromotionProject;

/**
 * 招商项目DAO接口
 * @author pengc
 * @version 2016-06-16
 */
@MyBatisDao
public interface PromotionProjectDao extends CrudDao<PromotionProject> {
	
}