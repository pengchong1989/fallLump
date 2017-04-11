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
		<li class="active"><a href="${ctx}/receivedata/receivedata/alarmList">节点告警状态列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="alarmData" action="${ctx}/receivedata/receivedata/alarmList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>段号ID：</label><form:input path="segmentId" htmlEscape="false" maxlength="50" class="input-medium"/>
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
				<th>上限位警报</th>
				<th>下限位警报</th>
				<th>高温警报</th>
				<th>低温警报</th>
				<th>设备倾斜警报</th>
				<th>采集时间</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dataAlarm">
			<tr>
				<td>${dataAlarm.segmentId}</td>
				<td>${dataAlarm.id}</td>
				<td>${dataAlarm.upAlarm}</td>
				<td>${dataAlarm.lowAlarm}</td>
				<td>${dataAlarm.highTemperatureAlarm}</td>
				<td>${dataAlarm.lowTemperatureAlarm}</td>
				<td>${dataAlarm.tiltAlarm}</td>
				<td><fmt:formatDate value="${dataAlarm.alarmDate}" type="both"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>