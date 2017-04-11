/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.user_info.web;

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
import com.thinkgem.jeesite.modules.user_info.entity.UserInfo;
import com.thinkgem.jeesite.modules.user_info.service.UserInfoService;

/**
 * 用户信息Controller
 * @author pengc
 * @version 2016-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/user_info/userInfo")
public class UserInfoController extends BaseController {

	@Autowired
	private UserInfoService userInfoService;
	
	@ModelAttribute
	public UserInfo get(@RequestParam(required=false) String id) {
		UserInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userInfoService.get(id);
		}
		if (entity == null){
			entity = new UserInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("user_info:userInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserInfo> page = userInfoService.findPage(new Page<UserInfo>(request, response), userInfo); 
		model.addAttribute("page", page);
		return "modules/user_info/userInfoList";
	}

	@RequiresPermissions("user_info:userInfo:view")
	@RequestMapping(value = "form")
	public String form(UserInfo userInfo, Model model) {
		model.addAttribute("userInfo", userInfo);
		return "modules/user_info/userInfoForm";
	}

	@RequiresPermissions("user_info:userInfo:edit")
	@RequestMapping(value = "save")
	public String save(UserInfo userInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userInfo)){
			return form(userInfo, model);
		}
		userInfoService.save(userInfo);
		addMessage(redirectAttributes, "保存用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/user_info/userInfo/?repage";
	}
	
	@RequiresPermissions("user_info:userInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(UserInfo userInfo, RedirectAttributes redirectAttributes) {
		userInfoService.delete(userInfo);
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/user_info/userInfo/?repage";
	}

}