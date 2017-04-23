/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.nodedata.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.constants.ZTConstants;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.NetUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.nodedata.entity.Nodedata;
import com.thinkgem.jeesite.modules.nodedata.service.NodedataService;
import com.thinkgem.jeesite.modules.operat_log.entity.OperatingConstants;
import com.thinkgem.jeesite.modules.receivedata.entity.Receivedata;
import com.thinkgem.jeesite.modules.receivedata.service.ReceivedataService;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;

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
		String status = Integer.toBinaryString(Integer.parseInt(nodedata.getCommand()));
		while(status.length()<8){
			status += "0";
		}
		nodedata.setCommand_restart(Integer.parseInt(status.substring(0, 1)));
		nodedata.setCommand_mpu_status(Integer.parseInt(status.substring(1, 2)));
		nodedata.setCommand_gprs_status(Integer.parseInt(status.substring(2, 3)));
		nodedata.setCommand_gps_status(Integer.parseInt(status.substring(3, 4)));
		nodedata.setCommand_other_status(Integer.parseInt(status.substring(4, 5)));
		nodedata.setCommand_sleep(Integer.parseInt(status.substring(5, 6)));
		nodedata.setCommand_update_time(Integer.parseInt(status.substring(6, 7)));
		nodedata.setCommand_update_time2(Integer.parseInt(status.substring(7, 8)));
		
		String status2 = Integer.toBinaryString(Integer.parseInt(nodedata.getMalfunctionStatus()));
		while(status.length()<8){
			status += "0";
		}
		model.addAttribute("nodedata", nodedata);
		return "modules/nodedata/nodedataForm";
	}

	@RequiresPermissions("nodedata:nodedata:edit")
	@RequestMapping(value = "save")
	public String save(Nodedata nodedata, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nodedata)){
			return form(nodedata, model);
		}
		
		nodedata.setUpdateDate(new Date(System.currentTimeMillis()));
		nodedataService.save(nodedata);
		if(org.apache.commons.lang3.StringUtils.isBlank(nodedata.getId())){//新建节点时，在最新的状态表中插入一条默认数据
			Receivedata receivedata = new Receivedata();
			receivedata.setSegmentIdMsb(nodedata.getSegmentIdMsb());
			receivedata.setMsbId(nodedata.getMsbId());
			receivedata.setId(IdGen.uuid());
			receivedataService.saveNew(receivedata);
		}
		LogUtils.saveOperatingLog(OperatingConstants.upLimitMsb, "配置菜单", nodedata.getUpLimitMsb());
		LogUtils.saveOperatingLog(OperatingConstants.lowerLimitMsb, "配置菜单", nodedata.getLowerLimitMsb());
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

	
	@RequestMapping("/list/exportPoi")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Nodedata nodedata) throws Exception{
		try { 
			request.setCharacterEncoding("UTF-8");
			//设置格式
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet= workbook.createSheet("sheet");
			HSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
			
			sheet.setColumnWidth(0, 4500);   
		    sheet.setColumnWidth(1, 4000);   
		    sheet.setColumnWidth(2, 3500);   
		    sheet.setColumnWidth(3, 3500);   
		    sheet.setColumnWidth(4, 4500);   
		    sheet.setColumnWidth(5, 3500);   
		    
			List<Nodedata> list=nodedataService.findList(nodedata);
			for(int i=-1;i<list.size();i++){
				Nodedata nodedata2 = list.get(i);
				//创建行
	            HSSFRow row = sheet.createRow(i+2);
	            row.setHeight((short)400);
	            //创建单元格
				HSSFCell cell0 = row.createCell(0);
				HSSFCell cell1 = row.createCell(1);
				HSSFCell cell2 = row.createCell(2);
				HSSFCell cell3 = row.createCell(3);
				HSSFCell cell4 = row.createCell(4);
				HSSFCell cell5 = row.createCell(5);
				HSSFCell cell6 = row.createCell(6);
				HSSFCell cell7 = row.createCell(7);
				HSSFCell cell8 = row.createCell(8);
				HSSFCell cell9 = row.createCell(9);
				HSSFCell cell10 = row.createCell(10);
				HSSFCell cell11 = row.createCell(11);
				HSSFCell cell12 = row.createCell(12);
				HSSFCell cell13 = row.createCell(13);
				HSSFCell cell14 = row.createCell(14);
				HSSFCell cell15 = row.createCell(15);
				HSSFCell cell16 = row.createCell(16);
				HSSFCell cell17 = row.createCell(17);
				HSSFCell cell18 = row.createCell(18);
				HSSFCell cell19 = row.createCell(19);
				HSSFCell cell20 = row.createCell(20);
				HSSFCell cell21 = row.createCell(21);
				HSSFCell cell22 = row.createCell(22);
				
				// 定义单元格为字符串类型
				cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell13.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell14.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell15.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell16.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell17.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell18.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell19.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell20.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell21.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell22.setCellType(HSSFCell.CELL_TYPE_STRING);
				//表头
				if(i == -1){
					cell0.setCellValue("ID");
					cell1.setCellValue("段 ID");
					cell2.setCellValue("激光测距传感器");
					cell3.setCellValue("芯片重启");
					cell4.setCellValue("激光测距、温度、MPU-6050传感器");
					cell5.setCellValue("GPRS传感器");
					cell6.setCellValue("GPS传感器");
					cell7.setCellValue("其它温度传感器数据控制");
					cell8.setCellValue("休眠");
					cell9.setCellValue("修改激光测距、温度、MPU-6050传感器定时器1时间");
					cell10.setCellValue("修改GPRS传感器定时器2时间");
					cell11.setCellValue("传感器时间1(min)");
					cell12.setCellValue("传感器时间2(min)");
					cell13.setCellValue("下限位距离(mm)");
					cell14.setCellValue("用户故障确认");
					cell15.setCellValue("用户故障排除");
					cell16.setCellValue("正在检修");
					cell17.setCellValue("手动重启");
					cell18.setCellValue("初始化配置标志");
					cell19.setCellValue("重启字节");
					cell20.setCellValue("A0");
					cell21.setCellValue("B0");
					cell22.setCellValue("C0");
				} else {
					cell0.setCellValue(nodedata2.getMsbId()==null?"":nodedata2.getMsbId());
					cell1.setCellValue(nodedata2.getSegmentIdMsb()==null?"":nodedata2.getSegmentIdMsb());
					cell2.setCellValue(nodedata2.getTestmsb()==null?"":nodedata2.getTestmsb()+"");
					cell3.setCellValue(nodedata2.getCommand_restart()==null?"":nodedata2.getCommand_restart()+"");
					cell4.setCellValue(nodedata2.getCommand_mpu_status()==null?"":nodedata2.getCommand_mpu_status()+"");
					cell5.setCellValue(nodedata2.getCommand_gprs_status()==null?"":nodedata2.getCommand_gprs_status()+"");
					cell6.setCellValue(nodedata2.getCommand_gps_status()==null?"":nodedata2.getCommand_gps_status()+"");
					cell7.setCellValue(nodedata2.getCommand_gps_status()==null?"":nodedata2.getCommand_gps_status()+"");
					cell8.setCellValue("休眠");
					cell9.setCellValue("修改激光测距、温度、MPU-6050传感器定时器1时间");
					cell10.setCellValue("修改GPRS传感器定时器2时间");
					cell11.setCellValue("传感器时间1(min)");
					cell12.setCellValue("传感器时间2(min)");
					cell13.setCellValue("下限位距离(mm)");
					cell14.setCellValue("用户故障确认");
					cell15.setCellValue("用户故障排除");
					cell16.setCellValue("正在检修");
					cell17.setCellValue("手动重启");
					cell18.setCellValue("初始化配置标志");
					cell19.setCellValue("重启字节");
					cell20.setCellValue("A0");
					cell21.setCellValue("B0");
					cell22.setCellValue("C0");
//					cell0.setCellValue(list.get(i).getName()== null?"":list.get(i).getName());//物资编码
//					cell1.setCellValue(list.get(i).getArea()== null?"":list.get(i).getArea());//通用名称
//					cell2.setCellValue(list.get(i).getLibrary_type() == null?"":list.get(i).getLibrary_type());//规格
//					cell3.setCellValue(list.get(i).getCollect_num()== null?"":list.get(i).getCollect_num()+"");//单位
//					cell4.setCellValue(list.get(i).getVisit_num()== null?"":list.get(i).getVisit_num()+"");//入库数量
//					cell5.setCellValue(list.get(i).getBookNum()== null?"":list.get(i).getBookNum()+"");//出库数量
				}
			}
			//第一行标题样式
			HSSFRow rowtitle1 = sheet.createRow(0);
			HSSFCell celltitle = rowtitle1.createCell(0);
			HSSFCellStyle cellStyle = workbook.createCellStyle();    //创建一个样式
			celltitle.setCellValue("活动报表");
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			rowtitle1.setHeightInPoints(30);
			cellStyle.setFont(this.getHdrFont(workbook));
			celltitle.setCellStyle(cellStyle);
			sheet.addMergedRegion(new Region(0,(short)0,0,(short)5)); 
			
			//下载方法
			this.sendExcel(response,workbook,"活动报表");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//下载文档
	public void sendExcel(HttpServletResponse response, HSSFWorkbook wb,String excelName) throws Exception {
		if(wb != null){
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition","attachment;filename="+new String(excelName.getBytes("gb2312"),"ISO8859-1")+".xls");		
			OutputStream ouputStream = null;
			try {
				ouputStream = response.getOutputStream();
				wb.write(ouputStream);
				ouputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					ouputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public  HSSFFont getHdrFont(HSSFWorkbook wb) {   
		HSSFFont fontStyle = wb.createFont();
		fontStyle = wb.createFont();    
		fontStyle.setFontName("宋体"); 
		fontStyle.setFontHeightInPoints((short)12);    
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    
		return fontStyle;    
	}
	
	
	public String exportFile(Nodedata nodedata,  HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "节点配置"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			List<Nodedata> list=nodedataService.findList(nodedata);

			new ExportExcel("内容分析数据", Nodedata.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ct/ctContent/?repage";
	}
}