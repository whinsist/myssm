<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="res/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="res/js/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="res/js/bootstrap.min.js"></script>
</head>
<body>

	<div style="padding: 0px; margin: 0px;">
		<ul class="breadcrumb" style="padding: 0px; padding-left: 20px;">
			<li><a href="#">首页</a></li>
			<li>工作台</li>
		</ul>
	</div>

	<div class="row " style="padding: 1px; margin: 0px;">
	

		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-refresh"></span>销售报表
				</div>
				<div class="panel-body">
					<img src="res/img/02.png" width="100%" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-refresh"></span>销售报表
				</div>
				<div class="panel-body">
					<img src="res/img/02.png" width="100%" />
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-refresh"></span>今日联系
				</div>
				<table class="table table-bordered">
					<tr>
						<th>联系人</th>
						<th>电话好吗</th>

					</tr>
					<tr>
						<td>张三</td>
						<td>1522355214</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-refresh"></span>今日联系
				</div>
				<table class="table table-bordered">
					<tr>
						<th>联系人</th>
						<th>电话好吗</th>

					</tr>
					<tr>
						<td>张三</td>
						<td>1522355214</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="panel panel-default">
				<div class="panel-heading" style="padding: 3px; height: 30px;">
					<span class="glyphicon glyphicon-refresh"></span>公告
				</div>
				<div class="panel-body">公告</div>
			</div>
		</div>
	</div>

</body>
</html>