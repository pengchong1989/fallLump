<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>receiveData管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/receivedata/receivedata/">节点状态列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="receivedata" action="${ctx}/receivedata/receivedata/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>段号ID：</label><form:input path="segmentIdMsb" htmlEscape="false" maxlength="50" class="input-medium"/>
		<label>ID：</label><form:input path="msbId" htmlEscape="false" maxlength="50" class="input-medium"/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="receivedata:receivedata:edit">
				<th>段ID</th>
				<th>ID</th>
				<th>温度传感器</th>
				<th>激光测距传感器</th>
				<th>A值</th>
				<th>B值</th>
				<th>X轴加速度</th>
				<th>Y轴加速度</th>
				<th>Z轴加速度</th>
				<th>X轴线速度</th>
				<th>Y轴线速度</th>
				<th>Z轴线速度</th>
				<th>经度</th>
				<th>纬度</th>
				<th>信号强度</th>
				<th>RTC时间</th>
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="receivedata">
			<tr>
				<td>${receivedata.segmentIdMsb}</td>
				<td>${receivedata.msbId}</td>
				<td>${receivedata.temperatureMsb}(°C)</td>
				<td>${receivedata.rangingMsb}(mm)</td>
				<td>${receivedata.aValue}</td>
				<td>${receivedata.bValue}</td>
				<td>${receivedata.accelerationXMsb}</td>
				<td>${receivedata.accelerationYMsb}</td>
				<td>${receivedata.accelerationZMsb}</td>
				<td>${receivedata.linespeedXMsb}</td>
				<td>${receivedata.linespeedYMsb}</td>
				<td>${receivedata.linespeedZMsb}</td>
				<td>${receivedata.gpsLng}</td>
				<td>${receivedata.gpsLat}</td>
				<td>${receivedata.signalStrengthMsb}(dBmW)</td>
				<td><fmt:formatDate value="${receivedata.rtcTime}" type="both"/></td>
				
				<shiro:hasPermission name="receivedata:receivedata:edit"><td>
    				<a href="${ctx}/receivedata/receivedata/form?id=${receivedata.id}">查看详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>