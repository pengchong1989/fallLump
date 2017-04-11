/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.user_additional.web;

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
import com.thinkgem.jeesite.modules.user_additional.entity.UserAdditional;
import com.thinkgem.jeesite.modules.user_additional.service.UserAdditionalService;

/**
 * 用户附加信息Controller
 * @author pengc
 * @version 2016-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/user_additional/userAdditional")
public class UserAdditionalController extends BaseController {

	@Autowired
	private UserAdditionalService userAdditionalService;
	
	@ModelAttribute
	public UserAdditional get(@RequestParam(required=false) String id) {
		UserAdditional entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userAdditionalService.get(id);
		}
		if (entity == null){
			entity = new UserAdditional();
		}
		return entity;
	}
	
	@RequiresPermissions("user_additional:userAdditional:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserAdditional userAdditional, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserAdditional> page = userAdditionalService.findPage(new Page<UserAdditional>(request, response), userAdditional); 
		model.addAttribute("page", page);
		return "modules/user_additional/userAdditionalList";
	}

	@RequiresPermissions("user_additional:userAdditional:view")
	@RequestMapping(value = "form")
	public String form(UserAdditional userAdditional, Model model) {
		model.addAttribute("userAdditional", userAdditional);
		return "modules/user_additional/userAdditionalForm";
	}

	@RequiresPermissions("user_additional:userAdditional:edit")
	@RequestMapping(value = "save")
	public String save(UserAdditional userAdditional, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userAdditional)){
			return form(userAdditional, model);
		}
		userAdditionalService.save(userAdditional);
		addMessage(redirectAttributes, "保存用户附加信息成功");
		return "redirect:"+Global.getAdminPath()+"/user_additional/userAdditional/?repage";
	}
	
	@RequiresPermissions("user_additional:userAdditional:edit")
	@RequestMapping(value = "delete")
	public String delete(UserAdditional userAdditional, RedirectAttributes redirectAttributes) {
		userAdditionalService.delete(userAdditional);
		addMessage(redirectAttributes, "删除用户附加信息成功");
		return "redirect:"+Global.getAdminPath()+"/user_additional/userAdditional/?repage";
	}

}