// JavaScript Document

$(document).ready(function(){
	//锚点滑动动画
	$('a[href*=#]').click(function() {  
		if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
			var $target = $(this.hash);  
			$target = $target.length && $target || $('[name=' + this.hash.slice(1) + ']');  
			if ($target.length) {
				var targetOffset = $target.offset().top;  
				$('html,body').animate({scrollTop: targetOffset},500);  
				return false;  
			}  
		}  
	}); 
	
	 
    //显示
    showOrHide();
    $(window).on("scroll",function(){
    	showOrHide();
    });
    
    //返回顶部
	$("#goTopBtn").click(function(){
	   $('body,html').animate({scrollTop:0},500);
	})
});
function showOrHide() {
	var top = $(document).scrollTop();
	console.log("top:" + top);
	if ($(document).scrollTop() > 250) {
		$(".jz_fh").show();
	} else {
		$(".jz_fh").hide();
	}
}




