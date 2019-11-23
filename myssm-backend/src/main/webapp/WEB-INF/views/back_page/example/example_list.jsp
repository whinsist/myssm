<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="menuKey" value="userList"/>
<html lang="en">
	<head>
		<title>后台管理-xxxxx列表</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="description" content="族库后台管理" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<%@ include file="../common/include.jsp"%>
	</head>
	<body class="no-skin">
		<!-- #头部信息 -->
		<%@ include file="../common/top.jspf"%>
		
		<!-- #内容部分 -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			
			<!-- #左侧菜单 -->
			<%@ include file="../common/left.jspf"%>

			<!-- #右侧内容 -->
			<div class="main-content">
				<div class="main-content-inner">
					<!-- #面包屑 -->
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="${pageContext.request.contextPath}/v1/back/page/main/index">首页</a>
							</li>
							<li class="active">xxxxx列表</li>
						</ul>
					</div>
					<!-- #页面内容 -->
					<div class="page-content">
						<div class="row h6">
							<form id="searchFrom">
								<div class="row h6">
									<div class="col-sm-1">
										<label class="control-label list_label col-sm-12 no-padding-right">账号：</label>
									</div>
									<div class="col-sm-1">
										<input class="form-control list_input" type="text" name="qm.accountLike"  placeholder="请输入姓名">
									</div> 
									<div class="col-sm-1">
										<label class="control-label list_label col-sm-12 no-padding-right">电话：</label>
									</div>
									<div class="col-sm-1">
										<input class="form-control list_input" type="text"  name="qm.mobileLike"  placeholder="请输入电话">
									</div>  
								</div>
							</form>
							<div class="row">
								<div class="col-sm-12 align-center">
									<button class="btn btn-white btn-info" id="searchButton">
										<i class="ace-icon fa fa-search nav-search-icon"></i>
										搜索
									</button>
									<button class="btn btn-white btn-info" id="refreshButton">
										<i class="ace-icon fa fa-retweet nav-search-icon"></i>
										重置
									</button>

									<button class="btn btn-white btn-info"  onclick="downloadUsers()">
										<i class="ace-icon fa fa-retweet nav-search-icon"></i>
										下载用户
									</button>
								</div>	
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<table id="grid-table"></table>
								<div id="grid-pager"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="dialog-addtemplet" class="hide">
				<div class="row">
					<form class="form-horizontal" method="post" action="" role="form" id="addForm" novalidate="novalidate">
						<div class="col-xs-12">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="userName"><span style="color: red;">*</span>用户名</label>
								<div class="col-sm-6">
									<input type="text" name="userName" id="form-userName" value="" maxlength="2000" placeholder="用户名" class="col-xs-10 col-sm-12">
								</div>
								<div class="col-sm-3">
									<span id="userName_fieldSet"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-12">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="phone">联系电话</label>
								<div class="col-sm-6">
									<input type="text" name="phone" id="phone" value="" maxlength="2000" placeholder="联系电话" class="col-xs-10 col-sm-12">
								</div>
								<div class="col-sm-3">
									<span id="phone_fieldSet"></span>
								</div>
							</div>
						</div>
						<div class="col-xs-12">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="userType">用户类型</label>
								<div class="col-sm-6">
									<input type="text" name="userType" id="form-userType" value="" maxlength="2000" placeholder="用户类型" class="col-xs-10 col-sm-12">
								</div>
								<div class="col-sm-3">
									<span id="userType_fieldSet"></span>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div id="dialog-confirm" class="hide">
				<p class="bigger-110 bolder center grey"  id="dialog-confirm-content">
				</p>
			</div>
			
			<!-- #页面底部 -->
			<%@ include file="../common/footer.jspf"%>
		</div>
		
		<!-- jqGrid -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/jqGrid/jquery.jqGrid.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/list.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/userinfo/js/userinfo_list.js"></script>
		<script type="text/javascript">
            setCurrentUserInfo();
			$(function(){
				//提示信息
				if('${message.type}' != null && '${message.type}' != ""){
					$.message('${message.type}','${message.content}');
				}
			});
		</script>
		<%@ include file="../common/login.jspf"%>
	</body>
</html>
