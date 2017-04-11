/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.nodedata.web;

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

import com.alibaba.druid.Constants;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.constants.ZTConstants;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.NetUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.nodedata.entity.Nodedata;
import com.thinkgem.jeesite.modules.nodedata.service.NodedataService;
import com.thinkgem.jeesite.modules.receivedata.entity.Receivedata;
import com.thinkgem.jeesite.modules.receivedata.service.ReceivedataService;

/**
 * nodeDataController
 * @author peng
 * @version 2017-02-13
 */
@Controller
@RequestMapping(value = "${adminPath}/nodedata/nodedata")
public class NodedataController extends BaseController {

	@Autowired
	private NodedataService nodedataService;
	
	@Autowired
	private ReceivedataService receivedataService;
	
	@ModelAttribute
	public Nodedata get(@RequestParam(required=false) String id) {
		Nodedata entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nodedataService.get(id);
		}
		if (entity == null){
			entity = new Nodedata(); 
		}
		return entity;
	}
	
	@RequiresPermissions("nodedata:nodedata:view")
	@RequestMapping(value = {"list", ""})
	public String list(Nodedata nodedata, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Nodedata> page = nodedataService.findPage(new Page<Nodedata>(request, response), nodedata); 
		model.addAttribute("page", page);
		return "modules/nodedata/nodedataList";
	}

	@RequiresPermissions("nodedata:nodedata:view")
	@RequestMapping(value = "form")
	public String form(Nodedata nodedata, Model model) {
		model.addAttribute("nodedata", nodedata);
		return "modules/nodedata/nodedataForm";
	}

	@RequiresPermissions("nodedata:nodedata:edit")
	@RequestMapping(value = "save")
	public String save(Nodedata nodedata, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nodedata)){
			return form(nodedata, model);
		}
		nodedataService.save(nodedata);
		if(org.apache.commons.lang3.StringUtils.isBlank(nodedata.getId())){//新建节点时，在最新的状态表中插入一条默认数据
			Receivedata receivedata = new Receivedata();
			receivedata.setSegmentIdMsb(nodedata.getSegmentIdMsb());
			receivedata.setMsbId(nodedata.getMsbId());
			receivedata.setId(IdGen.uuid());
			receivedataService.saveNew(receivedata);
		}
		//将最新配置的数据放入
		byte[] needs = getBytes(nodedata);
		JsonMapper jsonMapper = new JsonMapper();
		JedisUtils.setObject(ZTConstants.jedisKey.send_data_byte+nodedata.getSegmentIdMsb()+"_"+nodedata.getMsbId(), needs, 0);
		JedisUtils.set(ZTConstants.jedisKey.send_data_string+nodedata.getSegmentIdMsb()+"_"+nodedata.getMsbId(), jsonMapper.toJson(nodedata), 0);
		addMessage(redirectAttributes, "保存nodeData成功");
		return "redirect:"+Global.getAdminPath()+"/nodedata/nodedata/?repage";
	}
	
	/**
	 * 封装byte数组
	 * @param nodedata
	 * @return
	 */
	private byte[] getBytes(Nodedata nodedata){
		byte[] needs = new byte[23];
		needs[0] = (byte) 0xEB;
		needs[1] = (byte) 0x60;
		needs[2] = NetUtil.intToBytes(nodedata.getTestmsb())[2];
		needs[3] = NetUtil.intToBytes(nodedata.getTestmsb())[3];
		String command = ""+nodedata.getCommand_update_time2()+nodedata.getCommand_update_time()+nodedata.getCommand_sleep()+nodedata.getCommand_other_status()+
				nodedata.getCommand_gps_status()+nodedata.getCommand_gprs_status()+nodedata.getCommand_mpu_status()+nodedata.getCommand_restart();
		needs[4] = 	NetUtil.intToBytes(Integer.parseInt(command, 2))[3];
		needs[5] = NetUtil.intToBytes(Integer.parseInt(nodedata.getSensorTime1()))[3];
		needs[6] = NetUtil.intToBytes(Integer.parseInt(nodedata.getSensorTime2Msb()))[2];
		needs[7] = NetUtil.intToBytes(Integer.parseInt(nodedata.getSensorTime2Msb()))[3];
		needs[8] = NetUtil.intToBytes(Integer.parseInt(nodedata.getSegmentIdMsb()))[2];
		needs[9] = NetUtil.intToBytes(Integer.parseInt(nodedata.getSegmentIdMsb()))[3];
		needs[10] = NetUtil.intToBytes(Integer.parseInt(nodedata.getMsbId()))[2];
		needs[11] = NetUtil.intToBytes(Integer.parseInt(nodedata.getMsbId()))[3];
		needs[12] = NetUtil.intToBytes(Integer.parseInt(nodedata.getUpLimitMsb()))[2];
		needs[13] = NetUtil.intToBytes(Integer.parseInt(nodedata.getUpLimitMsb()))[3];
		needs[14] = NetUtil.intToBytes(Integer.parseInt(nodedata.getLowerLimitMsb()))[2];
		needs[15] = NetUtil.intToBytes(Integer.parseInt(nodedata.getLowerLimitMsb()))[3];
		String status = "000"+nodedata.getMalfunction_initialize()+nodedata.getMalfunction_restarting()+nodedata.getMalfunction_maintenance()+
				nodedata.getMalfunction_exclude()+nodedata.getMalfunction_confirm();
		needs[16] = NetUtil.intToBytes(Integer.parseInt(status, 2))[3];
		needs[17] = 0;
		needs[18] = 0;
		needs[19] = 0;
		needs[20] = NetUtil.checkNum(needs, 3, 20);
		needs[21] = 0x0d;
		needs[22] = 0x0a;
		NetUtil.print16String(needs);
		return needs;
	}
	
	@RequiresPermissions("nodedata:nodedata:edit")
	@RequestMapping(value = "delete")
	public String delete(Nodedata nodedata, RedirectAttributes redirectAttributes) {
		nodedataService.delete(nodedata);
		addMessage(redirectAttributes, "删除nodeData成功");
		return "redirect:"+Global.getAdminPath()+"/nodedata/nodedata/?repage";
	}

}