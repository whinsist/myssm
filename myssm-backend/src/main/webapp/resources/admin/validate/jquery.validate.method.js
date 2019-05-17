jQuery.validator.addMethod("integer", function(value, element) {return this.optional(element) || /^-?\d+$/.test(value)}, "A positive or negative non-decimal number please");
jQuery.validator.addMethod("positive", function(value, element) {return this.optional(element) || value > 0;}, "A positive number please");
jQuery.validator.addMethod("negative", function(value, element) {return this.optional(element) || value < 0;}, "A negative number please");
jQuery.validator.addMethod("decimal", function(value, element, param) {return this.optional(element) || new RegExp("^-?\\d{1," + (param.integer != null ? param.integer : "") + "}" + (param.fraction != null ? (param.fraction > 0 ? "(\\.\\d{1," + param.fraction + "})?$" : "$") : "(\\.\\d+)?$")).test(value);}, "numeric value out of bounds");
jQuery.validator.addMethod("pattern", function(value, element, param) {return this.optional(element) || param.test(value);}, "Invalid format");
jQuery.validator.addMethod("extension", function(value, element, param) {return this.optional(element) || ($.trim(param) != "" && new RegExp("^\\S.*\\.(" + param.replace(/,/g, "|") + ")$", "i").test(value));}, "Invalid extension");

jQuery.validator.addMethod("phone", function(value, element) {return this.optional(element) || /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(value)}, "A phone please");
jQuery.validator.addMethod("mobile", function(value, element) {return this.optional(element) || /^[1][0-9]{10}$/.test($.trim(value))}, "A mobile please");
jQuery.validator.addMethod("zipCode", function(value, element) {return this.optional(element) || /^[0-9]{6}$/.test(value)}, "A zipCode please");
jQuery.validator.addMethod("username", function(value, element) {return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value)}, "A username please");
jQuery.validator.addMethod("positiveInteger", function(value, element) {return this.optional(element) || /^[1-9]\d*$/.test(value)}, "A username please");

jQuery.validator.addMethod("money", function(value, element) {return this.optional(element) ||  /^[0-9]+(.[0-9]{1,2})?$/.test(value)}, "A money please");
jQuery.validator.addMethod("fax", function(value, element) {return this.optional(element) ||  /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/.test(value)}, "A money please");
jQuery.validator.addMethod("code", function(value, element) {return this.optional(element) ||  /^[A-Za-z0-9]+$/.test(value)}, "A numer or words please");
jQuery.validator.addMethod("words", function(value, element) {return this.optional(element) ||  /^[A-Za-z]+$/.test(value)}, "A words please");

jQuery.validator.addMethod("startWithWord", function(value, element) {return this.optional(element) ||  /^[a-zA-Z]{1}.*$/.test(value)}, "A words please");


jQuery.validator.addMethod("ydmobile", function(value, element) {return this.optional(element) ||   /^(((13)[4-9]{1})|((14)[7]{1})|((15)[0,1,2,7,8,9]{1})|((17)[8]{1})|(18[2,3,4,7,8]{1}))\d{8}$/.test(value)}, "A mobile please");
jQuery.validator.addMethod("username2", function(value, element) {return this.optional(element) || /^[\u4E00-\u9FA5A-Za-z0-9\(\)（）]+$/.test(value)}, "A username please");

jQuery.validator.addMethod("idCard", function(value, element) {return this.optional(element) ||  /^(\d{18,18}|\d{15,15}|(\d{17,17}[x,X]{1}))$/.test(value)}, "A I.D. card please");

jQuery.validator.addMethod("illegalWord", function(value, element) {return this.optional(element) ||  !/^[<,>]+$/.test(value)}, "A illegal");


jQuery.extend(jQuery.validator.messages, {
	required:"必填",
	email: "格式错误",
	url: "格式错误",
	date: "格式错误",
	dateISO: "格式错误",
	pointcard: "格式错误",
	number: "数字",
	digits: "零或正整数",
	minlength: jQuery.validator.format("长度不小于{0}"),
	maxlength: jQuery.validator.format("长度不大于{0}"),
	rangelength: jQuery.validator.format("长度在{0}-{1}之间"),
	min: jQuery.validator.format("不小于{0}"),
	max: jQuery.validator.format("不大于{0}"),
	range: jQuery.validator.format("在{0}-{1}之间"),
	accept: "输入后缀错误",
	equalTo: "两次输入不一致",
	remote: "已存在",
	integer: "整数",
	positive: "大于0",
	negative: "负数",
	positiveInteger: "必须是正整数",
	mobile: "格式错误",
	phone: "格式错误",
	zipCode: "邮政编码格式错误",
	username: "中文、英文、数字和下划线",
	decimal: "数值超出范围",
	pattern: "格式错误",
	extension: "文件格式错误",
	fax: "传真号码格式错误",
	money: "金额格式错误",
	code: "数字和字母",
	words: "输入字母",
	filesize:"文件不能超过{0}K",
	startWithWord:"字母开头",
	ydmobile:"非移动手机号码",
	idCard:"身份证号码格式错误",
	username2: "中文、英文、数字、括号",
	illegalWord:"含有非法字符<、>"
});