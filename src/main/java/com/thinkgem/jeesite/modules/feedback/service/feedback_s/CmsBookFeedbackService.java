/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.feedback.service.feedback_s;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.feedback.entity.feedback_s.CmsBookFeedback;
import com.thinkgem.jeesite.modules.feedback.dao.feedback_s.CmsBookFeedbackDao;

/**
 * feedback_functionService
 * @author feedback_author
 * @version 2016-06-14
 */
@Service
@Transactional(readOnly = true)
public class CmsBookFeedbackService extends CrudService<CmsBookFeedbackDao, CmsBookFeedback> {

	public CmsBookFeedback get(String id) {
		return super.get(id);
	}
	
	public List<CmsBookFeedback> findList(CmsBookFeedback cmsBookFeedback) {
		return super.findList(cmsBookFeedback);
	}
	
	public Page<CmsBookFeedback> findPage(Page<CmsBookFeedback> page, CmsBookFeedback cmsBookFeedback) {
		return super.findPage(page, cmsBookFeedback);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsBookFeedback cmsBookFeedback) {
		super.save(cmsBookFeedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsBookFeedback cmsBookFeedback) {
		super.delete(cmsBookFeedback);
	}
	
}