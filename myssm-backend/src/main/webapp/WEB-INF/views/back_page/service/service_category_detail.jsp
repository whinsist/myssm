<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="menuKey" value="serviceCategoryList"/>
<html lang="en">
	<head>
		<title>后台管理-服务目录详情</title>
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
							<li class="active">服务详情及申请</li>
						</ul>
					</div>
					<!-- #页面内容 -->
					<div class="page-content">
                        <input type="hidden" value="${id}" id="id">

                        <form class="form-horizontal" method="post" data-validator-option="{theme:'yellow_top',stopOnError:true}">
                            <h5 class="page-header alert-info" style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
                            <div class="row">
                                <div class="col-xs-5">
                                    <div class="form-group ">
                                        <label class="col-xs-3 control-label">服务目录名称</label>
                                        <div class="col-xs-9 ">
                                            <input type="text" class="form-control"  id="serviceName" name="serviceName" value=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-5">
                                    <div class="form-group ">
                                        <label class="col-xs-3 control-label">实例名称</label>
                                        <div class="col-xs-9">
                                            <input type="text" class="form-control" value="whest-vm"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>








						<form class="form-horizontal" method="post" data-validator-option="{theme:'yellow_top',stopOnError:true}">
							<input type="button" id="appyServiceCategoryBtn" class="btn btn-success" value="申请服务" />
						</form>


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
			var serviceCategoryId = $("#id").val();
            $(function(){
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/v1/back/service_category/" + serviceCategoryId,
                    success: function (data) {
                        var serviceCategory = data.data;
                        $("#serviceName").val(serviceCategory.serviceName);
                    }
                });
            });

            $("#appyServiceCategoryBtn").click(function () {

                var params = {
                    "name": $("#serviceName").val(),
                    //"ownerId": 398,
                    "processFlag": "03",
                    "serviceId": serviceCategoryId,
                    "type": "apply"
                }

                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/v1/back/service_category/apply",
                    contentType: 'application/json; charset=UTF-8',
                    data: JSON.stringify(params),
                    success: function (data) {
                        var code = data.code;
                        if (code == 200) {
                            $.message("success", "重新部署流程定义成功！");
                            window.location.reload();
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