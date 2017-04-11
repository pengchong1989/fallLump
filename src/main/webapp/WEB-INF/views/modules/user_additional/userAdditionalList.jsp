<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户附加信息管理</title>
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
		<li class="active"><a href="${ctx}/user_additional/userAdditional/">用户附加信息列表</a></li>
		<shiro:hasPermission name="user_additional:userAdditional:edit"><li><a href="${ctx}/user_additional/userAdditional/form">用户附加信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="userAdditional" action="${ctx}/user_additional/userAdditional/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<shiro:hasPermission name="user_additional:userAdditional:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userAdditional">
			<tr>
				<td><a href="${ctx}/user_additional/userAdditional/form?id=${userAdditional.id}">
					${userAdditional.name}
				</a></td>
				<shiro:hasPermission name="user_additional:userAdditional:edit"><td>
    				<a href="${ctx}/user_additional/userAdditional/form?id=${userAdditional.id}">修改</a>
					<a href="${ctx}/user_additional/userAdditional/delete?id=${userAdditional.id}" onclick="return confirmx('确认要删除该用户附加信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>