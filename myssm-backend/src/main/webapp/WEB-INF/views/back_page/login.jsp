<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
	<head>
		<title>管理后台-登录</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta name="description" content="管理" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.css" />
		<!-- text fonts -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-fonts.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.css" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-part2.css" />
		<![endif]-->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/css/common_admin.css" />
		
		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-ie.css" />
		<![endif]-->
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="${pageContext.request.contextPath}/resources/assets/js/html5shiv.js"></script>
		<script src="${pageContext.request.contextPath}/resources/assets/js/respond.js"></script>
		<![endif]-->
		<style type="text/css">
			.login-container {
				width: 375px;
    			margin: 0 auto;
    			margin-top: 12%;
    		}
		</style>
	</head>
	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">八戒家装后台管理</span>
								</h1>
							</div>
							<div class="space-6"></div>
							<div class="position-relative">
								<!-- 登录框 -->
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												请输入您的信息
											</h4>
											<div class="space-6"></div>
											<form id="loginForm" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" id="username" name="username" value="admin" maxlength="20" class="form-control" placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" id="password" name="password" value="1q2w3e4r"  maxlength="20" class="form-control" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right row" >
															<img src="" id="captchaImg" onclick="freshCaptcha()" style="vertical-align:middle "/>
															<input type="text" id="captcha" name="captcha" value=""  maxlength="20">
															<input type="hidden" id="captchaKey" name="captchaKey" value=""/>
														</span>
													</label>
													<div class="space"></div>
													<div class="clearfix">
														<button type="button" id="loginButton" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">登录</span>
														</button>
													</div>
													<div class="space-4"></div>
												</fieldset>
											</form>
										</div>
									</div>
								</div>
							</div>
							<div class="navbar-fixed-top align-right">
								<br />
								&nbsp;
								<a id="btn-login-dark" href="#">Dark</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="#">Blur</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="#">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="baseUrl" value="${pageContext.request.contextPath}">
		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/resources/assets/js/jquery.js'>"+"<"+"/script>");
		</script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${pageContext.request.contextPath}/resources/assets/js/jquery1x.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/resources/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/admin_login_constant.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/common_admin.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/message/jquery.message.js"></script>	
		<!-- 登录、登出控制 -->	
		<%@ include file="common/login.jspf"%>
		
		
		
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			
			//改变背景
			jQuery(function($) {
				
				//默认皮肤
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');
				
			 	$('#btn-login-dark').on('click', function(e) {
					$('body').attr('class', 'login-layout');
					$('#id-text2').attr('class', 'white');
					$('#id-company-text').attr('class', 'blue');
				
					e.preventDefault();
			 	});
			 	$('#btn-login-light').on('click', function(e) {
					$('body').attr('class', 'login-layout light-login');
					$('#id-text2').attr('class', 'grey');
					$('#id-company-text').attr('class', 'blue');
				
					e.preventDefault();
			 	});
				 $('#btn-login-blur').on('click', function(e) {
					$('body').attr('class', 'login-layout blur-login');
					$('#id-text2').attr('class', 'white');
					$('#id-company-text').attr('class', 'light-blue');
					
					e.preventDefault();
				 });


                freshCaptcha();


			});

			function freshCaptcha() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/v1/captcha/info",
                    type: "GET",
                    data: {},
                    success: function (data) {
                        $("#captchaImg").attr("src", data.data.captchaBase64Str);
                        $("#captchaKey").val(data.data.captchaKey);
                    }
                })
            }

		</script>
	</body>
</html>