<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>招商政策管理</title>
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
		<li class="active"><a href="${ctx}/investment_policy/investmentPolicy/">招商政策列表</a></li>
		<shiro:hasPermission name="investment_policy:investmentPolicy:edit"><li><a href="${ctx}/investment_policy/investmentPolicy/form">招商政策添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="investmentPolicy" action="${ctx}/investment_policy/investmentPolicy/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<shiro:hasPermission name="investment_policy:investmentPolicy:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="investmentPolicy">
			<tr>
				<td><a href="${ctx}/investment_policy/investmentPolicy/form?id=${investmentPolicy.id}">
					${investmentPolicy.title}
				</a></td>
				<shiro:hasPermission name="investment_policy:investmentPolicy:edit"><td>
    				<a href="${ctx}/investment_policy/investmentPolicy/form?id=${investmentPolicy.id}">修改</a>
					<a href="${ctx}/investment_policy/investmentPolicy/delete?id=${investmentPolicy.id}" onclick="return confirmx('确认要删除该招商政策吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>