$(function() {

	$.validator.addClassRules({
		albumFile : {
			extension : "jpg,bmp,png"
		},
		albumOrder : {
			digits : true
		}
	});

	$("#inputForm").validate({
		rules : {
			'goodsName' : {
				required : true,
				maxlength : 50
			},
			'price' : {
				required : true
			}
		},
		errorClass : "fieldError",
		ignore : ".ignore",
		ignoreTitle : true,
		errorPlacement : function(error, element) {
			var element_id = element.attr("name");
			var fieldSet = null;
			if (element_id == 'albumOrderList') {
				fieldSet = $(element).parent().parent().find(
						"span[class='album_fieldSet']");
			} else if (element_id == 'albumFile') {
				fieldSet = $(element).parent().parent().parent().find(
						"span[class='album_fieldSet']");
			} else {
				fieldSet = $("#" + element_id + '_fieldSet');
			}

			if (fieldSet != null) {
				error.appendTo(fieldSet);
			} else {
				error.insertAfter(element);
			}
		},
		submitHandler : function(form) {
			//验证图片是否上传
			/*var pic = $("#pic").val();
			if(isNullOrUndefined(pic)){
				$.message("warn", "图片未上传");
				return;
			}
			*/
			
			form.submit();
		}
	});

});
