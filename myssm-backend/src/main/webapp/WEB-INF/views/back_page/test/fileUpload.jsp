<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="menuKey" value="userList"/>
<html lang="en">
	<head>
		<title>测试-上传文件</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="description" content="族库后台管理" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/message/jquery.message.css" />
	</head>
	<body class="no-skin">
		 
		
		<!-- #内容部分 -->
		<div>
			<div>
				<div>上传地址：${fileServerUploadUrl}</div>
				<div>下载地址：${fileServerDownloadUrl}</div>
				<div>浏览地址：${fileServerBrowseUrl}</div>
				<input type="hidden" readonly="readonly" id="fileServerUploadUrl" value="${fileServerUploadUrl}">
				<input type="hidden" readonly="readonly" id="fileServerDownloadUrl" value="${fileServerDownloadUrl}">
				<input type="hidden" readonly="readonly" id="fileServerBrowseUrl" value="${fileServerBrowseUrl}">
			</div>
			 
			 <div>
			 	上传Excel：<input type="file" id="excelFile">
			 	<a href="" id="uploadExcelMsg" style="display:none;">下载</a>
			 	<input type="text" id="uploadExcelTime" value="0">
			 </div>

			 <div>
			 	上传文件：<input type="file" id="uploadFile">
			 	<a href="" id="uploadFileMsg" style="display:none;">下载</a>
			 	<input type="text" id="uploadFileTime" value="0">
			 </div>

			 <div>
			 	上传图片： <input type="file" id="uploadImage">
			 	<a href="" id="uploadImageMsg" style="display:none;">下载</a>
				<img src="${pageContext.request.contextPath}/resources/admin/images/loading.gif" id="loading" style="display:none;" />
				<br/>
				<img src="" id="imageoriginal" style="border:1px solid gray;"><br/>
				<img src="" id="image200" style="border:1px solid gray;"><br/>
				<img src="" id="image400" style="border:1px solid gray;"><br/>
			</div>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/message/jquery.message.js"></script>
		<script type="text/javascript">
			 
			
			
			var fileServerUploadUrl = $("#fileServerUploadUrl").val();
			var fileServerDownloadUrl = $("#fileServerDownloadUrl").val();
			var fileServerBrowseUrl = $("#fileServerBrowseUrl").val();

            $('#excelFile').on('change', function() {
                //1.获取文件信息
                var fileName = $("#excelFile").get(0).files[0].name;
                var fileExt =  fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
                var fileSize = $("#excelFile").get(0).files[0].size;
                //2.验证是否可以上传
                if(fileSize > 104857600){
                    $.message('error',"文件大于10M");
                    return;
                }
                //3.开始上传
                var fd = new FormData();
                fd.append("files", $("#excelFile").get(0).files[0]);
                $.ajax({
                    url: "${pageContext.request.contextPath}/v1/back/users/import",
                    timeout : 10000, //超时时间设置，单位毫秒
                    type: "POST",
                    processData: false,
                    contentType: false,
                    data: fd,
                    beforeSend: function(XMLHttpRequest){
                        $("#loading").show();
                        $("#uploadExcelTime").val("0");
                        startTimer();
                    },
                    success: function(data){
                        var id =  data.invokerResult.content[0].id;
                        $("#uploadExcelMsg").attr("href", fileServerDownloadUrl+id+".do").show();
                    },
                    complete : function(XMLHttpRequest,status) {
                        endTimer();
                        $("#loading").hide();
                        if(status=='timeout') {
                            $.message('error',"上传文件请求超时...");
                            return;
                        }
                    }
                });
            });
			
			$('#uploadFile').on('change', function() {
				//1.获取文件信息
				var fileName = $("#uploadFile").get(0).files[0].name;
				var fileExt =  fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
				var fileSize = $("#uploadFile").get(0).files[0].size;
				//2.验证是否可以上传
				if(fileSize > 104857600){
					$.message('error',"文件大于10M");
					return;
				}
				//3.开始上传
				var fd = new FormData();
				fd.append("files", $("#uploadFile").get(0).files[0]);
				$.ajax({
					url: fileServerUploadUrl,
					timeout : 10000, //超时时间设置，单位毫秒
					type: "POST",
					processData: false,
					contentType: false,
					data: fd,
					beforeSend: function(XMLHttpRequest){
						$("#loading").show();
						$("#uploadFileTime").val("0");
						startTimer();
					},
					success: function(data){
						var id =  data.invokerResult.content[0].id;
						$("#uploadFileMsg").attr("href", fileServerDownloadUrl+id+".do").show();
					},
					complete : function(XMLHttpRequest,status) { 
						endTimer();
						$("#loading").hide();
						if(status=='timeout') {
							$.message('error',"上传文件请求超时...");
							return; 
						}
				    }
				});
			});
			
			
			$('#uploadImage').on('change', function() {
				//判断文件后缀名 仅支持：jpg、bmp、png
				var fileName = $("#uploadImage").get(0).files[0].name;
				var extension =  fileName.substring(fileName.lastIndexOf('.')+1);
				var uploadFlag = false;
				var uploadMessage;
				//判断文件后缀
				extension = extension.toLowerCase();
				if(extension != 'jpg' && extension != 'bmp' && extension != 'png'){
					uploadFlag = true;
					uploadMessage = '文件格式不正确,仅支持:jpg|bmp|png';
				}
				//判断大小:1M
				var fileSize = $("#uploadImage").get(0).files[0].size;
				if(fileSize > 1048576) {
					uploadFlag = true;
					uploadMessage = '文件大小不能超过1M';
				}
				if(uploadFlag) {
					$.message('error',uploadMessage);
					return;
				}else {
					var fd = new FormData();
					fd.append("format", '200,200;400,400');
					fd.append("files", $("#uploadImage").get(0).files[0]);
					$.ajax({
						url: fileServerUploadUrl,
						timeout : 10000,  
						type: "POST",
						processData: false,
						contentType: false,
						data: fd,
						beforeSend: function(XMLHttpRequest){
							$("#loading").show();
						},
						success: function(data){
							var orgId =  data.invokerResult.content[0].id;
							var id200 =  data.invokerResult.content[1].id;
							var id400 =  data.invokerResult.content[2].id;
							$("#imageoriginal").attr("src", fileServerBrowseUrl+orgId+".do");
							$("#image200").attr("src", fileServerBrowseUrl+id200+".do");
							$("#image400").attr("src", fileServerBrowseUrl+id400+".do");
							
							$("#uploadImageMsg").attr("href", fileServerDownloadUrl+orgId+".do").show();
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
							var errorMessage = textStatus=='timeout'?"上传文件请求超时...":textStatus;
	                        $("#i_h_background_image").val("");	
							$.message('error', errorMessage);
							return; 
	                    },
						complete : function(XMLHttpRequest,status) { 
							$("#loading").hide();
					    }
					});
				}
			});
			
			
			var timer;
			function startTimer(){
				var num = parseInt($("#uploadFileTime").val()) +1;
				$("#uploadFileTime").val(num);
				console.log(num);
				timer = setTimeout("startTimer()",1000);//调用自己
			}
			function endTimer(){
				clearTimeout(timer)
			}
		</script>
	</body>
</html>
