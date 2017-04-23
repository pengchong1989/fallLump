<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>操作日志管理</title>
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
		<li class="active"><a href="${ctx}/operat_log/operatingLog/">操作日志列表</a></li>
		<shiro:hasPermission name="operat_log:operatingLog:edit"><li><a href="${ctx}/operat_log/operatingLog/form">操作日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="operatingLog" action="${ctx}/operat_log/operatingLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>参数标题：</label>
				<input id="title" name="title" value="${operatingLog.title}" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>日期范围：&nbsp;</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${operatingLog.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li><label style="width: 40px;text-align: center">&nbsp;--&nbsp;&nbsp;</label><input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${operatingLog.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>操作菜单</th>
				<th>参数标题</th>
				<th>参数值</th>
				<th>操作人员</th>
				<th>操作时间</th>
				<shiro:hasPermission name="operat_log:operatingLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="operatingLog">
			<tr>
				<td>${operatingLog.type}</td>
				<td><a href="${ctx}/operat_log/operatingLog/form?id=${operatingLog.id}">
					${operatingLog.title}
				</a></td>
				<td>${operatingLog.params}</td>
				<td>${operatingLog.createBy.name}</td>
				<td><fmt:formatDate value="${operatingLog.createDate}" type="both"/></td>
				<shiro:hasPermission name="operat_log:operatingLog:edit"><td>
    				<a href="${ctx}/operat_log/operatingLog/form?id=${operatingLog.id}">修改</a>
					<a href="${ctx}/operat_log/operatingLog/delete?id=${operatingLog.id}" onclick="return confirmx('确认要删除该操作日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>