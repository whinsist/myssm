//手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

//联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isPhone", function(value,element) {
  var length = value.length;
  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
  var tel = /^\d{3,4}-?\d{7,9}$/;
  return this.optional(element) || (tel.test(value) || length == 11&&mobile.test(value));
}, "请正确填写您的联系电话"); 

//非法字符验证
jQuery.validator.addMethod("isLegal", function(value,element) {
  var role = /[`@#^<>"{}\/'[\]]/im;
  return this.optional(element) || !role.test(value);
}, "包含非法字符"); 

//重复值验证
jQuery.validator.addMethod("isRepeat", function(value,element) {
	var id =element.id;
	var returnValue=true;
	 jQuery("[name*=compactNo_]").each(function(){
		 if (jQuery(this).attr("id")!=id&&jQuery(this).val()==value) {
			 returnValue=false;
		}
	 });
	 return this.optional(element) || returnValue;
}, "重复的合同编号"); 



function addinput(){
	var lastId=$("[name*=compactNo_]:last").attr("id");
	var maxId=lastId.split("_")[1];
	var count=$(".compactNoAdd").size();
	var id = parseInt(maxId)+1;
	if (count<5) {
		jQuery("#addinput").parent().before('<li class="compactNoAdd"><span class="webname"> <input name="compactNo_'+id+'" id = "compactNo_'+id+'" type="text" value="" /></span><a href="javascript:void(0);" onclick="delinput(this);" title="删除"><img src="$!webPath/resources/style/system/manage/blue/images/remove_text.png" /></a></li> ');
		jQuery("#compactNo_"+id).rules("add", {
			 maxlength: 20,
			 isLegal:true,
			 isRepeat:true,
			 remote:{
				  url:"$!webPath/verify_compact.htm",
				  type : "get",
				  dataType:"json",
		          data : {
		              "compact_no": function(){return jQuery("#compactNo_"+id).val();},"id":"$!obj.id"
				  }
				 },
	       messages: {
	    	   maxlength: "不能超过20位",
	    	   isLegal:"包含非法字符",
	    	   remote:"合同编号已存在"
	       }
	     });   
	}else{
		alert("最多只能添加五个合同编号！");
	}
}