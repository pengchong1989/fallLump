<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>nodeData管理</title>
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
		<li class="active"><a href="${ctx}/nodedata/nodedata/">节点配置列表</a></li>
		<shiro:hasPermission name="nodedata:nodedata:edit"><li><a href="${ctx}/nodedata/nodedata/form">节点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="nodedata" action="${ctx}/nodedata/nodedata/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="nodedata:nodedata:edit">
				<th>段ID</th>
				<th>ID</th>
				<th>传感器定时器1</th>
				<th>传感器定时器2</th>
				<th>上限位距离</th>
				<th>下限位距离</th>
				<th>激光传感器距离</th>
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="nodedata">
			<tr>
				<td>${nodedata.segmentIdMsb}</td>
				<td>${nodedata.msbId}</td>
				<td>${nodedata.sensorTime1}</td>
				<td>${nodedata.sensorTime2Msb}</td>
				<td>${nodedata.upLimitMsb}</td>
				<td>${nodedata.lowerLimitMsb}</td>
				<td>${nodedata.testmsb}</td>
				<shiro:hasPermission name="nodedata:nodedata:edit"><td>
    				<a href="${ctx}/nodedata/nodedata/form?id=${nodedata.id}">修改</a>
					<a href="${ctx}/nodedata/nodedata/delete?id=${nodedata.id}" onclick="return confirmx('确认要删除该nodeData吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>