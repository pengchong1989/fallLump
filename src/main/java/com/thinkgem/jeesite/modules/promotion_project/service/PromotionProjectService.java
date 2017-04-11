/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.promotion_project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.promotion_project.entity.PromotionProject;
import com.thinkgem.jeesite.modules.promotion_project.dao.PromotionProjectDao;

/**
 * 招商项目Service
 * @author pengc
 * @version 2016-06-16
 */
@Service
@Transactional(readOnly = true)
public class PromotionProjectService extends CrudService<PromotionProjectDao, PromotionProject> {

	public PromotionProject get(String id) {
		return super.get(id);
	}
	
	public List<PromotionProject> findList(PromotionProject promotionProject) {
		return super.findList(promotionProject);
	}
	
	public Page<PromotionProject> findPage(Page<PromotionProject> page, PromotionProject promotionProject) {
		return super.findPage(page, promotionProject);
	}
	
	@Transactional(readOnly = false)
	public void save(PromotionProject promotionProject) {
		super.save(promotionProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(PromotionProject promotionProject) {
		super.delete(promotionProject);
	}
	
}