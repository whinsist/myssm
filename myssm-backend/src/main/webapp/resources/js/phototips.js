$(function(){
	var x = 10;
	var y = 20;
	$("img.tooltip").mouseover(function(e){
		this.myTitle = this.title;
		this.title = ""; 
		var imgTitle = this.myTitle? "<br/>" + this.myTitle : ""; 
		var tooltip = "<div id='tooltip'><img src='"+ this.src +"' alt='封面' onerror='this.src=\"/images\/tm.gif\"'  width='"+parseInt(this.width)*6+"' height='"+parseInt(this.height)*6+"'/><\/div>"; //创建 div 元素
		 // alert(tooltip);
		$("body").append(tooltip);	//把它追加到文档中						 
		$("#tooltip")
			.css({
				"top": (e.pageY+y) + "px",
				"left":  (e.pageX+x)  + "px"
			}).show("fast");	  //设置x坐标和y坐标，并且显示
    }).mouseout(function(){
		this.title = this.myTitle;	
		$("#tooltip").remove();	 //移除 
    }).mousemove(function(e){
		$("#tooltip")
			.css({
				"top": (e.pageY+y) + "px",
				"left":  (e.pageX+x)  + "px"
			});
	});
})