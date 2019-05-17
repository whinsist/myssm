
(function($) {
	var zIndex = 100;
	var $message;
	var messageTimer;
	/**
	 * $.message("success","操作成功");
	 * $.message("warn","操作失败");
	 * $.message({"type":"success","content":"操作成功","time":1000});
	 */
	$.message = function() {
		var message = {};
		//得到初始化参数
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
		} else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.content = arguments[1];
			message.time = arguments[2];
		} else {
			return false;
		}
		if (message.type == null || message.content == null) {
			return false;
		}
		if (message.time == null){
			message.time = 3000;
		}

		//生成消息提示框HTML
		if ($message == null) {
			$message = $('<div class="xxMessage"><div class="messageContent message' + message.type + 'Icon"><\/div><\/div>');
			if (!window.XMLHttpRequest) {
				$message.append('<iframe class="messageIframe"><\/iframe>');
			}
			$message.appendTo("body"); 
		}
		
		$message.children("div")
		.removeClass("messagewarnIcon messageerrorIcon messagesuccessIcon")
		.addClass("message" + message.type + "Icon").html(message.content);
		
		/*$(".messageContent").hover(function(){
		    alert(1);
		},function(){
			alert(2);
		});*/
		
		$message.css({"margin-left": - parseInt($message.outerWidth() / 2), "z-index": zIndex ++}).show();
		//定时关闭消息提示框
		clearTimeout(messageTimer);
		messageTimer = setTimeout(function() {
			$message.hide();
		}, message.time);
		return $message;
	}

	// 对话框
	$.dialog = function(options) {}
})(jQuery);

 