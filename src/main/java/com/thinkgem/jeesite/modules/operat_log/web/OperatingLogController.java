/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.operat_log.web;

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
import com.thinkgem.jeesite.modules.operat_log.entity.OperatingLog;
import com.thinkgem.jeesite.modules.operat_log.service.OperatingLogService;

/**
 * 操作日志Controller
 * @author admin
 * @version 2017-04-23
 */
@Controller
@RequestMapping(value = "${adminPath}/operat_log/operatingLog")
public class OperatingLogController extends BaseController {

	@Autowired
	private OperatingLogService operatingLogService;
	
	@ModelAttribute
	public OperatingLog get(@RequestParam(required=false) String id) {
		OperatingLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = operatingLogService.get(id);
		}
		if (entity == null){
			entity = new OperatingLog();
		}
		return entity;
	}
	
	@RequiresPermissions("operat_log:operatingLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(OperatingLog operatingLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OperatingLog> page = operatingLogService.findPage(new Page<OperatingLog>(request, response), operatingLog); 
		model.addAttribute("page", page);
		return "modules/operat_log/operatingLogList";
	}

	@RequiresPermissions("operat_log:operatingLog:view")
	@RequestMapping(value = "form")
	public String form(OperatingLog operatingLog, Model model) {
		model.addAttribute("operatingLog", operatingLog);
		return "modules/operat_log/operatingLogForm";
	}

	@RequiresPermissions("operat_log:operatingLog:edit")
	@RequestMapping(value = "save")
	public String save(OperatingLog operatingLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, operatingLog)){
			return form(operatingLog, model);
		}
		operatingLogService.save(operatingLog);
		addMessage(redirectAttributes, "保存操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/operat_log/operatingLog/?repage";
	}
	
	@RequiresPermissions("operat_log:operatingLog:edit")
	@RequestMapping(value = "delete")
	public String delete(OperatingLog operatingLog, RedirectAttributes redirectAttributes) {
		operatingLogService.delete(operatingLog);
		addMessage(redirectAttributes, "删除操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/operat_log/operatingLog/?repage";
	}

}