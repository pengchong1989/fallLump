/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.promotion_project.web;

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
import com.thinkgem.jeesite.modules.promotion_project.entity.PromotionProject;
import com.thinkgem.jeesite.modules.promotion_project.service.PromotionProjectService;

/**
 * 招商项目Controller
 * @author pengc
 * @version 2016-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/promotion_project/promotionProject")
public class PromotionProjectController extends BaseController {

	@Autowired
	private PromotionProjectService promotionProjectService;
	
	@ModelAttribute
	public PromotionProject get(@RequestParam(required=false) String id) {
		PromotionProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = promotionProjectService.get(id);
		}
		if (entity == null){
			entity = new PromotionProject();
		}
		return entity;
	}
	
	@RequiresPermissions("promotion_project:promotionProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(PromotionProject promotionProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PromotionProject> page = promotionProjectService.findPage(new Page<PromotionProject>(request, response), promotionProject); 
		model.addAttribute("page", page);
		return "modules/promotion_project/promotionProjectList";
	}

	@RequiresPermissions("promotion_project:promotionProject:view")
	@RequestMapping(value = "form")
	public String form(PromotionProject promotionProject, Model model) {
		model.addAttribute("promotionProject", promotionProject);
		return "modules/promotion_project/promotionProjectForm";
	}

	@RequiresPermissions("promotion_project:promotionProject:edit")
	@RequestMapping(value = "save")
	public String save(PromotionProject promotionProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, promotionProject)){
			return form(promotionProject, model);
		}
		promotionProjectService.save(promotionProject);
		addMessage(redirectAttributes, "保存招商项目成功");
		return "redirect:"+Global.getAdminPath()+"/promotion_project/promotionProject/?repage";
	}
	
	@RequiresPermissions("promotion_project:promotionProject:edit")
	@RequestMapping(value = "delete")
	public String delete(PromotionProject promotionProject, RedirectAttributes redirectAttributes) {
		promotionProjectService.delete(promotionProject);
		addMessage(redirectAttributes, "删除招商项目成功");
		return "redirect:"+Global.getAdminPath()+"/promotion_project/promotionProject/?repage";
	}

}