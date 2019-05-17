<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<div>
查询手机所在地：<br/>
<input type="text" value="" name="phone" id="phone"/> <input type="button" id="phoneQuery" value="查询"/>
<div id="phoneMessage" style="margin-top: 20px;"></div>
</div>

<div>
查询天气情况：<br/>
省：<select id="province">
		<option value="0">请选择</option>
	</select>
 
省：<select id="city">
		<option value="0">请选择</option>
	</select> 
<div id="base"></div>
<div id="message"></div>

<div>一周情况:</div>
<table id="table" width="800"></table>
</div>



<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#phoneQuery").on("click",function(){
			var phone = $("#phone").val();
			$.ajax({
				type:"post",
				url:"/ws/queryPhone.shtml",
				data:{"phone":phone},
				success:function(data){
					var content = data.content;
					var info = "手机号码："+content.phone+", 省份："+content.province+", 城市："+content.city+", 手机卡类型："+content.type;
					$("#phoneMessage").html("").html(info);
				}
			});
		});
		
		
		var province = [{id:'31123',name:'四川'},{id:'311104',name:'重庆'}];
		for(var i=0;i<province.length;i++){
			$("#province").append("<option value='"+province[i].id+"'>"+province[i].name+"</option>");
		}
		
		
		$("#province").on("change",function(){
			$.ajax({
				url: "/ws/queryWether.shtml",
				data: {"type":"getCity","provinceId":$(this).val()},
				success: function(data){
					var content = data.content;
					$("#city").empty();
					$.each(content, function(index,item){
						$("#city").append("<option value='"+item.id+"'>"+item.name+"</option>");
					});
				}
			});
		});
	 	
		
		$("#city").on("change",function(){
			$.ajax({
				url: "/ws/queryWether.shtml",
				data: {"type":"getWeather","cityId":$(this).val()},
				success: function(data){
					var content = data.content;
					
					//显示基本信息
					$("#base").empty().html(content.cityName+" "+content.todayZwx); 
					
					//城市名称 
					var cityNameHtml = content.cityName;
					var imgUrl = "/resources/specials/ws/images/weather/";
					//只当天的 ：图片   温度
					var day0 = content.list[0];
					var imgHtml = "<img src='"+imgUrl+day0.img1+"'/>"+"<img src='"+imgUrl+day0.img2+"'/>"
					var wdHtml = "<span>"+day0.tq+"</span>";
					$("#message").empty().html(cityNameHtml+imgHtml+wdHtml);
					
					
					//显示所有天信息
					$("#table").empty();
					$.each(content.list, function(index,item){
						var imgHtml = "<img src='"+imgUrl+item.img1+"'/>"+"<img src='"+imgUrl+item.img2+"'/>"
						var trhtml = "<tr><td>"+item.gk+"</td><td>"+item.tq+"</td><td>"+imgHtml+"</td></tr>"
						$("#table").append(trhtml);
					});   
					
				}
			});
		});
		
	});
</script>





</body>
</html>