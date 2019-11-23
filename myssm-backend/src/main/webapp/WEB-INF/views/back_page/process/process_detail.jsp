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
						<input type="hidden" value="${id}" id="id">
						<input type="hidden" value="" id="nodeNameSortNum">

						
						<!-- 添加表单 -->
						<form class="form-horizontal" method="post" data-validator-option="{theme:'yellow_top',stopOnError:true}">
							<h5 class="page-header alert-info" style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
							<div class="row">
								<div class="col-xs-5">
									<div class="form-group ">
										<label class="col-xs-3 control-label">流程名称</label>
										<div class="col-xs-9 ">
											<input type="text" id="processName" name="processName" data-rule="账号:required;" class="form-control" placeholder="请输入流程名称" readonly />
										</div>
									</div>
								</div>
								<div class="col-xs-5">
									<div class="form-group ">
										<label class="col-xs-3 control-label">流程类型</label>
										<div class="col-xs-5 ">
											<select class="form-control" name="businessCode" id="businessCode" readonly="">
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
											<input type="text" class="form-control" id="description" name="description" readonly/>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-5">
									<div class="form-group ">
										<label class="col-xs-3 control-label">流程图</label>
										<div class="col-xs-9">
											<img src="" id="processImage"/>
										</div>
									</div>
								</div>
							</div>
						</form>
						<!-- 添加表单end -->




						<form class="form-horizontal" method="post" data-validator-option="{theme:'yellow_top',stopOnError:true}">
							<input type="button" id="addNodeBtn" class="btn btn-success" value="增加环节(默认值)" />
							<input type="button" id="deployNodeBtn" class="btn btn-success" value="发布" />
						</form>


						<form class="form-horizontal" method="post" data-validator-option="{theme:'yellow_top',stopOnError:true}">
							<h5 class="page-header alert-info" style="margin: 0px; padding: 10px; margin-bottom: 10px;">环节</h5>
							<table id="nodeList" class="table table-bordered">
								<tr><td>环节编号</td><td>环节名称</td><td>描述</td><td>处理人</td></tr>
							</table>
						</form>


						<div id="dialog-addtemplet" class="hide">
							<div class="row">
								<form class="form-horizontal" method="post" action="" role="form" id="addForm" novalidate="novalidate">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" ><span style="color: red;">*</span>环节名称</label>
											<div class="col-sm-6">
												<input type="text" name="userName" id="form-userName" value="" maxlength="2000" placeholder="环节名称" class="col-xs-10 col-sm-12">
											</div>
											<div class="col-sm-3">
												<span id="userName_fieldSet"></span>
											</div>
										</div>
									</div>
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="phone">审核人</label>
											<div class="col-sm-6">
												<input type="text" name="phone" id="phone" value="" maxlength="2000" placeholder="审核人" class="col-xs-10 col-sm-12">
											</div>
											<div class="col-sm-3">
												<span id="phone_fieldSet"></span>
											</div>
										</div>
									</div>
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right">审核提示</label>
											<div class="col-sm-6">
												<input type="text" name="userType" id="form-userType" value="" maxlength="2000" placeholder="审核提示" class="col-xs-10 col-sm-12">
											</div>
											<div class="col-sm-3">
												<span id="userType_fieldSet"></span>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>

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
			var processId = $("#id").val();
            $(function(){
                initDetail();
			});

            function initDetail() {
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/v1/back/process/detail/" + processId,
                    success: function (data) {
                        var process = data.data.process;
                        $("#processName").val(process.name);
                        $("#businessCode").val(process.businessCode);
                        $("#description").val(process.description);
                        $("#processImage").attr("src", "data:image/png;base64,"+process.image);
                        $("#nodeList").html("");
                        var html = "";
                        var sortNum = 1;
                        var records = data.data.records;
                        for (var i=0; i< records.length; i++) {
                            var item = records[i];
                            var x = "<tr><td>"+item.id+"</td><td>"+item.nodeName+"</td><td>描述</td><td>"+item.configData+"</td></tr>";
                            html += x;

                            var words = item.nodeName.split("环节");
                            if (words.length > 1) {
                                sortNum = parseInt(words[1]) > sortNum ? parseInt(words[1]) +1 : sortNum;
                            }
                        }
                        $("#nodeList").append(html);
                        $("#nodeNameSortNum").val(sortNum);
                    }
                });
			}



            $("#addNodeBtn").click(function () {
                $("#dialog-addtemplet").removeClass('hide').dialog({
                    modal: true,
                    width: 600,
                    title: "<div>增加环节</div>",
                    title_html: true,
                    buttons: [{
                        text: "取消",
                        "class" : "btn btn-minier",
                        click: function() {
                            $("#dialog-addtemplet").dialog( "close" );
                        }
                    },
                        {
                            text: "确定",
                            "class" : "btn btn-primary btn-minier",
                            click: function() {
                                submitAddNode();
                                $("#dialog-addtemplet").dialog( "close" );
                            }
                        }]
                });
            });



            
            function submitAddNode() {
                var params = {
                    "processId": processId,
                    "nodeName": "环节"+$("#nodeNameSortNum").val(),
                    "candidates": [
                        {
                            "type":"10-platform",
                            "name":"组织管理员(内置)",
                            "refId":"101"
                        }
                    ],
                    "candidateThirds":[
                        {
                            "name":"yangqin",
                            "refId":398
                        }
                    ],
                    "notifyWays":[
                        "station"
                    ]
                };
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/v1/back/process/node/add",
                    contentType: 'application/json; charset=UTF-8',
                    data: JSON.stringify(params),
                    success: function (data) {
                        var code = data.code;
                        if (code == 200) {
                            $.message("success", "增加默认环节成功！");
                            //window.location.reload();
                            initDetail();
                        } else{
                            $.message("warn", data.message);
                        }
                    }
                });
            }



            $("#deployNodeBtn").click(function () {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/v1/back/process/deploy/" + processId,
                    contentType: 'application/json; charset=UTF-8',
                    success: function (data) {
                        var code = data.code;
                        if (code == 200) {
                            $.message("success", "重新部署流程定义成功！");
                            // window.location.reload();
                            initDetail();
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
