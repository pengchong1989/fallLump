<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>nodeData管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/nodedata/nodedata/">节点配置列表</a></li>
		<li class="active"><a href="${ctx}/nodedata/nodedata/form?id=${nodedata.id}">节点<shiro:hasPermission name="nodedata:nodedata:edit">${not empty nodedata.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="nodedata:nodedata:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="nodedata" action="${ctx}/nodedata/nodedata/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">段 ID ：</label>
			<div class="controls">
				<form:input path="segmentIdMsb" htmlEscape="false" maxlength="10" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ID：</label>
			<div class="controls">
				<form:input path="msbId" htmlEscape="false" maxlength="10" class="required digits"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">激光测距传感器：</label>
			<div class="controls">
				<form:input path="testmsb" htmlEscape="false"  maxlength="3" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">芯片重启：</label>
			<div class="controls">
				<form:select path="command_restart">
					<form:options items="${fns:getDictList('command_restart')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">激光测距、温度、MPU-6050传感器：</label>
			<div class="controls">
				<form:select path="command_mpu_status">
					<form:options items="${fns:getDictList('command_restart')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">GPRS传感器：</label>
			<div class="controls">
				<form:select path="command_gprs_status">
					<form:options items="${fns:getDictList('command_restart')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">GPS传感器：</label>
			<div class="controls">
				<form:select path="command_gps_status">
					<form:options items="${fns:getDictList('command_restart')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其它温度传感器数据控制：</label>
			<div class="controls">
				<form:select path="command_other_status">
					<form:options items="${fns:getDictList('command_restart')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">休眠：</label>
			<div class="controls">
				<form:select path="command_sleep">
					<form:options items="${fns:getDictList('command_sleep')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">修改激光测距、温度、MPU-6050传感器定时器1时间：</label>
			<div class="controls">
				<form:select path="command_update_time">
					<form:options items="${fns:getDictList('command_sleep')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">修改GPRS传感器定时器2时间：</label>
			<div class="controls">
				<form:select path="command_update_time2">
					<form:options items="${fns:getDictList('command_sleep')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">传感器时间1(min)：</label>
			<div class="controls">
				<form:input path="sensorTime1" htmlEscape="false"  maxlength="10" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传感器时间2(min)：</label>
			<div class="controls">
				<form:input path="sensorTime2Msb" htmlEscape="false" maxlength="10" class="required digits"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">上限位距离(mm)：</label>
			<div class="controls">
				<form:input path="upLimitMsb" htmlEscape="false" maxlength="10" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下限位距离(mm)：</label>
			<div class="controls">
				<form:input path="lowerLimitMsb" htmlEscape="false" maxlength="10" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户故障确认：</label>
			<div class="controls">
				<form:select path="malfunction_confirm">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">用户故障排除：</label>
			<div class="controls">
				<form:select path="malfunction_exclude">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正在检修：</label>
			<div class="controls">
				<form:select path="malfunction_maintenance">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手动重启：</label>
			<div class="controls">
				<form:select path="malfunction_restarting">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
			
		</div>
		<div class="control-group">
			<label class="control-label">初始化配置标志：</label>
			<div class="controls">
				<form:select path="malfunction_initialize">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">初始化配置标志：</label>
			<div class="controls">
				<form:select path="restart">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">A0：</label>
			<div class="controls">
				<form:input path="a0" htmlEscape="false"  maxlength="10" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">B0：</label>
			<div class="controls">
				<form:input path="b0" htmlEscape="false"  maxlength="10" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">C0：</label>
			<div class="controls">
				<form:input path="c0" htmlEscape="false"  maxlength="10" class="required digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="nodedata:nodedata:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>