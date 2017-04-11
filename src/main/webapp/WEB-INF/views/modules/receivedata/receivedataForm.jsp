<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>receiveData管理</title>
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
		<li><a href="${ctx}/receivedata/receivedata/">节点状态列表</a></li>
		<li class="active"><a href="${ctx}/receivedata/receivedata/form?id=${receivedata.id}">节点状态<shiro:hasPermission name="receivedata:receivedata:edit">${not empty receivedata.id?'查看':'添加'}</shiro:hasPermission><shiro:lacksPermission name="receivedata:receivedata:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="receivedata" action="${ctx}/receivedata/receivedata/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">段ID：</label>
			<div class="controls">
				<form:input path="segmentIdMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">ID：</label>
			<div class="controls">
				<form:input path="msbId" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">温度传感器字节MSB（温度放大100倍）：</label>
			<div class="controls">
				<form:input path="temperatureMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">激光测距传感器：</label>
			<div class="controls">
				<form:input path="rangingMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重力加速度传感器字节X轴加速度：</label>
			<div class="controls">
				<form:input path="accelerationXMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重力加速度传感器字节Y轴加速度：</label>
			<div class="controls">
				<form:input path="accelerationYMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重力加速度传感器字节Z轴加速度：</label>
			<div class="controls">
				<form:input path="accelerationZMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重力加速度传感器字节X轴线速度：</label>
			<div class="controls">
				<form:input path="linespeedXMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重力加速度传感器字节Y轴线速度：</label>
			<div class="controls">
				<form:input path="linespeedYMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重力加速度传感器字节Z轴线速度：</label>
			<div class="controls">
				<form:input path="linespeedZMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测试字节MSB：</label>
			<div class="controls">
				<form:input path="testMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测试字节LSB：</label>
			<div class="controls">
				<form:input path="testLsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">gps经度：</label>
			<div class="controls">
				<form:input path="gpsLng" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">gps纬度：</label>
			<div class="controls">
				<form:input path="gpsLat" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">rtc时间：</label>
			<div class="controls">
				<input name="rtcTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${receivedata.rtcTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">信号强度：</label>
			<div class="controls">
				<form:input path="signalStrengthMsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">握手确认状态：</label>
			<div class="controls">
				<form:input path="handshakeStatus" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电池容量：</label>
			<div class="controls">
				<form:input path="batteryCapacity" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电池温度：</label>
			<div class="controls">
				<form:input path="batteryTemperature" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电池状态：</label>
			<div class="controls">
				<form:input path="batteryStatus" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">amsb：</label>
			<div class="controls">
				<form:input path="amsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">alsb：</label>
			<div class="controls">
				<form:input path="alsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">bmsb：</label>
			<div class="controls">
				<form:input path="bmsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">blsb：</label>
			<div class="controls">
				<form:input path="blsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">cmsb：</label>
			<div class="controls">
				<form:input path="cmsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">clsb：</label>
			<div class="controls">
				<form:input path="clsb" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>