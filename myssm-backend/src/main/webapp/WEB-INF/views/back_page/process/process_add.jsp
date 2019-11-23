<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="menuKey" value="processAdd"/>
<html lang="en">
	<head>
		<title>后台管理-流程添加</title>
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
							<li class="active">流程添加</li>
						</ul>
					</div>
					<!-- #页面内容 -->
					<div class="page-content">
						
						<!-- 添加表单 -->
						<form class="form-horizontal" method="post" data-validator-option="{theme:'yellow_top',stopOnError:true}">
							<h5 class="page-header alert-info" style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>


							<div class="row">
								<div class="col-xs-5">
									<div class="form-group ">
										<label class="col-xs-3 control-label">流程名称</label>
										<div class="col-xs-9 ">
											<input type="text" id="processName" name="processName" data-rule="账号:required;" class="form-control" placeholder="请输入流程名称" />
										</div>
									</div>
								</div>
								<div class="col-xs-5">
									<div class="form-group ">
										<label class="col-xs-3 control-label">流程类型</label>
										<div class="col-xs-5 ">
											<select class="form-control" name="businessCode" id="businessCode">
												<option value="service-apply">服务开通</option>
												<option value="service-execScript">执行脚本</option>
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-5">
									<div class="form-group ">
										<label class="col-xs-3 control-label">描述</label>
										<div class="col-xs-9">
											<input type="text" class="form-control" id="description" name="description"/>
										</div>
									</div>
								</div>
							</div>





							<div class="row">
								<div class="col-xs-3 col-xs-offset-4">
									<input type="button" id="submitBtn" class="btn btn-success" value="保存流程" />
									<input type="reset" class="btn btn-danger" value="重置信息" />
								</div>
							</div>
						</form>
						<!-- 添加表单end -->
					</div>
				</div>
			</div>
			<!-- #页面底部 -->
			<%@ include file="../common/footer.jspf"%>
		</div>
		
		<!-- jqGrid -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/jqGrid/jquery.jqGrid.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script type="text/javascript">
			$("#submitBtn").click(function () {
                var params = {
                    "processName": $("#processName").val(),
					"businessCode": $("#businessCode").val(),
					"description": $("#description").val()
                };

                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/v1/back/process/add",
                    contentType: 'application/json; charset=UTF-8',
                    data: JSON.stringify(params),
                    success: function (data) {
                        var code = data.code;
                        if (code == 200) {
                            $.message("success", data.message);
                            location.href = "${pageContext.request.contextPath}/v1/back/page/process/process_list"
                        } else{
                            $.message("warn", data.message);
                        }
                    }
                });

            });

		</script>
		<%@ include file="../common/login.jspf"%>
	</body>
</html>
