<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电影项目管理</title>
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
		<li class="active"><a href="${ctx}/movie_ip/movieIp/">电影项目列表</a></li>
		<shiro:hasPermission name="movie_ip:movieIp:edit"><li><a href="${ctx}/movie_ip/movieIp/form">电影项目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="movieIp" action="${ctx}/movie_ip/movieIp/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>片名：</label>
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
				<th>片名</th>
				<shiro:hasPermission name="movie_ip:movieIp:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="movieIp">
			<tr>
				<td><a href="${ctx}/movie_ip/movieIp/form?id=${movieIp.id}">
					${movieIp.title}
				</a></td>
				<shiro:hasPermission name="movie_ip:movieIp:edit"><td>
    				<a href="${ctx}/movie_ip/movieIp/form?id=${movieIp.id}">修改</a>
					<a href="${ctx}/movie_ip/movieIp/delete?id=${movieIp.id}" onclick="return confirmx('确认要删除该电影项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>