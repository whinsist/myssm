var file_upload="http://dl.bajiegc.com/resManager/upload.json";
var file_download = "http://dl.bajiegc.com/resManager/download/";

/**
 * js公共方法
 * 
 * @date:2016-01-05
 * @author:yuhaibo
 */
//判断变量是否未定义或者空
function isNullOrUndefined(str)
{
	if (typeof(str)!="undefined" && str!=null && str!= 'null' && str!= ' ' && str!= "" && str!= " ")
	{
		return false;
	}else
	{
		return true;
	}
}

//添加Cookie
function addCookie(name, value, options) 
{
	if (arguments.length > 1 && name != null) 
	{
		if (options == null) 
		{
			options = {};
		}
		if (value == null) 
		{
			options.expires = -1;
		}
		if (typeof options.expires == "number") 
		{
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path : "") + (options.domain ? "; domain=" + options.domain : ""), (options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) 
{
	if (name != null)
	{
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) 
{
	addCookie(name, null, options);
}


//输出信息
function message(code) 
{
	var content = messages[code] != null ? messages[code] : code;
	return content;
}
//格式化时间
function formatDate(date,format) {  
    var o = {  
      "M+" : date.getMonth()+1, //month  
      "d+" : date.getDate(),    //day  
      "h+" : date.getHours(),   //hour  
      "m+" : date.getMinutes(), //minute  
      "s+" : date.getSeconds(), //second  
      "q+" : Math.floor((date.getMonth()+3)/3),  //quarter  
      "S" : date.getMilliseconds() //millisecond  
    }  
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,(date.getFullYear()+"").substr(4 - RegExp.$1.length));  
    for(var k in o) if(new RegExp("("+ k +")").test(format))  
        format = format.replace(RegExp.$1,  
        RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));  
    return format;  
} 

(function($) {
	/*var zIndex = 100;
	// 消息框
	var $message;
	var messageTimer;
	$.message = function() {
		var message = {};
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
		} else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.content = arguments[1];
		} else {
			return false;
		}
		
		if (message.type == null || message.content == null) {
			return false;
		}
		
		if ($message == null) {
			$message = $('<div class="xxMessage"><div class="messageContent message' + message.type + 'Icon"><\/div><\/div>');
			if (!window.XMLHttpRequest) {
				$message.append('<iframe class="messageIframe"><\/iframe>');
			}
			$message.appendTo("body");
		}
		
		$message.children("div").removeClass("messagewarnIcon messageerrorIcon messagesuccessIcon").addClass("message" + message.type + "Icon").html(message.content);
		$message.css({"margin-left": - parseInt($message.outerWidth() / 2), "z-index": zIndex ++}).show();
		
		clearTimeout(messageTimer);
		messageTimer = setTimeout(function() {
			$message.hide();
		}, 3000);
		return $message;
	}*/

	// 对话框
	$.dialog = function(options) {
		var settings = {
			width: 320,
			height: "auto",
			modal: true,
			ok: message("确&nbsp;&nbsp;定"),
			cancel: message("取&nbsp;&nbsp;消"),
			onShow: null,
			onClose: null,
			onOk: null,
			onCancel: null
		};
		$.extend(settings, options);
		
		if (settings.content == null) {
			return false;
		}
		
		var $dialog = $('<div class="xxDialog"><\/div>');
		var $dialogTitle;
		var $dialogClose = $('<div class="dialogClose"><\/div>').appendTo($dialog);
		var $dialogContent;
		var $dialogBottom;
		var $dialogOk;
		var $dialogCancel;
		var $dialogOverlay;
		if (settings.title != null) {
			$dialogTitle = $('<div class="dialogTitle"><\/div>').appendTo($dialog);
		}
		if (settings.type != null) {
			$dialogContent = $('<div class="dialogContent dialog' + settings.type + 'Icon"><\/div>').appendTo($dialog);
		} else {
			$dialogContent = $('<div class="dialogContent"><\/div>').appendTo($dialog);
		}
		if (settings.ok != null || settings.cancel != null) {
			$dialogBottom = $('<div class="dialogBottom"><\/div>').appendTo($dialog);
		}
		if (settings.ok != null) {
			$dialogOk = $('<input type="button" class="button" value="' + settings.ok + '" \/>').appendTo($dialogBottom);
		}
		if (settings.cancel != null) {
			$dialogCancel = $('<input type="button" class="button" value="' + settings.cancel + '" \/>').appendTo($dialogBottom);
		}
		if (!window.XMLHttpRequest) {
			$dialog.append('<iframe class="dialogIframe"><\/iframe>');
		}
		$dialog.appendTo("body");
		if (settings.modal) {
			$dialogOverlay = $('<div class="dialogOverlay"><\/div>').insertAfter($dialog);
		}
		
		var dialogX;
		var dialogY;
		if (settings.title != null) {
			$dialogTitle.text(settings.title);
		}
		$dialogContent.html(settings.content);
		$dialog.css({"width": settings.width, "height": settings.height, "margin-left": - parseInt(settings.width / 2), "z-index": zIndex ++});
		dialogShow();
		
		if ($dialogTitle != null) {
			$dialogTitle.mousedown(function(event) {
				$dialog.css({"z-index": zIndex ++});
				var offset = $(this).offset();
				if (!window.XMLHttpRequest) {
					dialogX = event.clientX - offset.left;
					dialogY = event.clientY - offset.top;
				} else {
					dialogX = event.pageX - offset.left;
					dialogY = event.pageY - offset.top;
				}
				$("body").bind("mousemove", function(event) {
					$dialog.css({"top": event.clientY - dialogY, "left": event.clientX - dialogX, "margin": 0});
				});
				return false;
			}).mouseup(function() {
				$("body").unbind("mousemove");
				return false;
			});
		}
		
		if ($dialogClose != null) {
			$dialogClose.click(function() {
				dialogClose();
				return false;
			});
		}
		
		if ($dialogOk != null) {
			$dialogOk.click(function() {
				if (settings.onOk && typeof settings.onOk == "function") {
					if (settings.onOk($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}
		
		if ($dialogCancel != null) {
			$dialogCancel.click(function() {
				if (settings.onCancel && typeof settings.onCancel == "function") {
					if (settings.onCancel($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}
		
		function dialogShow() {
			if (settings.onShow && typeof settings.onShow == "function") {
				if (settings.onShow($dialog) != false) {
					$dialog.show();
					$dialogOverlay.show();
				}
			} else {
				$dialog.show();
				$dialogOverlay.show();
			}
		}
		function dialogClose() {
			if (settings.onClose && typeof settings.onClose == "function") {
				if (settings.onClose($dialog) != false) {
					$dialogOverlay.remove();
					$dialog.remove();
				}
			} else {
				$dialogOverlay.remove();
				$dialog.remove();
			}
		}
		return $dialog;
	}
})(jQuery);

(function(window) {
    var theUA = window.navigator.userAgent.toLowerCase();
    if ((theUA.match(/msie\s\d+/) && theUA.match(/msie\s\d+/)[0]) || (theUA.match(/trident\s?\d+/) && theUA.match(/trident\s?\d+/)[0])) {
        var ieVersion = theUA.match(/msie\s\d+/)[0].match(/\d+/)[0] || theUA.match(/trident\s?\d+/)[0];
        if (ieVersion <= 11) {
            $("body").css("overflow","hidden")
            var str = "你的浏览器版本太low了\n已经和时代脱轨了 :(";
            var str2 = "<div style='font-size: 22px; color: #666; text-align: left; padding-left: 30px;'><a class='low_a' href='https://www.baidu.com/s?ie=UTF-8&wd=%E8%B0%B7%E6%AD%8C%E6%B5%8F%E8%A7%88%E5%99%A8' target='_blank' style='text-decoration: none; color:#ff6900; font-size: 22px;'>谷歌</a>,"
                + "<a class='low_a' href='https://www.baidu.com/s?ie=UTF-8&wd=%E7%81%AB%E7%8B%90%E6%B5%8F%E8%A7%88%E5%99%A8' target='_blank' style='text-decoration: none; color:#ff6900; font-size: 22px;'>火狐</a>,"
                + "其他双核急速模式</div>";
            document.writeln("<div style='clear: both; text-align:center;color:#fff;background-color:#fff; height:100%;border:0;position:fixed;top:0;left:0;width:100%;z-index:10000; font-family: Microsoft YaHei;'><div style='width: 990px; margin: 0 auto; background-color: #fff;'>" +
                "<h2 style='float: left; '><img style='width: 430px;' id='LowImg1' src='images/low_img_02.jpg'/></h2><div style='float: left; width: 560px;'><img id='LowImg2' src='images/low_img_03.jpg'/>" +
            str2 + "<h2><img id='LowImg3' src='images/low_img_06.jpg'/></h2></div></div></div><style>a.low_a:hover{text-decoration: underline !important;}</style>");
            document.execCommand("Stop");
        };
    }
})(window);
$(document).ready(function(){
    $("#LowImg1").attr("src",$("#baseUrl").val()+"/resources/bimLibrary/admin/images/low_img_02.jpg");
    $("#LowImg2").attr("src",$("#baseUrl").val()+"/resources/bimLibrary/admin/images/low_img_03.jpg");
    $("#LowImg3").attr("src",$("#baseUrl").val()+"/resources/bimLibrary/admin/images/low_img_06.jpg");
});




 








