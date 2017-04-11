/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.feedback.web.feedback_s;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.feedback.entity.feedback_s.CmsBookFeedback;
import com.thinkgem.jeesite.modules.feedback.service.feedback_s.CmsBookFeedbackService;

/**
 * feedback_functionController
 * @author feedback_author
 * @version 2016-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/feedback/feedback_s/cmsBookFeedback")
public class CmsBookFeedbackController extends BaseController {

	@Autowired
	private CmsBookFeedbackService cmsBookFeedbackService;
	
	@ModelAttribute
	public CmsBookFeedback get(@RequestParam(required=false) String id) {
		CmsBookFeedback entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsBookFeedbackService.get(id);
		}
		if (entity == null){
			entity = new CmsBookFeedback();
		}
		return entity;
	}
	
	@RequiresPermissions("feedback:feedback_s:cmsBookFeedback:view")
	@RequestMapping(value = {"list", ""})
	public String list(CmsBookFeedback cmsBookFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsBookFeedback> page = cmsBookFeedbackService.findPage(new Page<CmsBookFeedback>(request, response), cmsBookFeedback); 
		model.addAttribute("page", page);
		return "modules/feedback/feedback_s/cmsBookFeedbackList";
	}

	@RequiresPermissions("feedback:feedback_s:cmsBookFeedback:view")
	@RequestMapping(value = "form")
	public String form(CmsBookFeedback cmsBookFeedback, Model model) {
		model.addAttribute("cmsBookFeedback", cmsBookFeedback);
		return "modules/feedback/feedback_s/cmsBookFeedbackForm";
	}

	@RequiresPermissions("feedback:feedback_s:cmsBookFeedback:edit")
	@RequestMapping(value = "save")
	public String save(CmsBookFeedback cmsBookFeedback, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsBookFeedback)){
			return form(cmsBookFeedback, model);
		}
		cmsBookFeedbackService.save(cmsBookFeedback);
		addMessage(redirectAttributes, "保存feedback_name成功");
		return "redirect:"+Global.getAdminPath()+"/feedback/feedback_s/cmsBookFeedback/?repage";
	}
	
	@RequiresPermissions("feedback:feedback_s:cmsBookFeedback:edit")
	@RequestMapping(value = "delete")
	public String delete(CmsBookFeedback cmsBookFeedback, RedirectAttributes redirectAttributes) {
		cmsBookFeedbackService.delete(cmsBookFeedback);
		addMessage(redirectAttributes, "删除feedback_name成功");
		return "redirect:"+Global.getAdminPath()+"/feedback/feedback_s/cmsBookFeedback/?repage";
	}

}