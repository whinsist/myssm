
1、用户session保存一个标识 防止重复提交
	第一步：
	@RequestMapping("/admin/store_new.htm")
	String cart_session = CommUtil.randomString(32);
	request.getSession(false).setAttribute("cart_session", cart_session);
	第二步:
	store_new.html
	<form name="theForm" id="theForm" action="$!webPath/admin/store_save.htm" method="post">
		<input name="cart_session" value="$!cart_session" id="cart_session" type="hidden"/>
	</form>
	第三步:
	@RequestMapping("/admin/store_save.htm")
	String cart_session1 = (String) request.getSession(false).getAttribute("cart_session");
	if(cart_session.equals(cart_session1)){
		request.getSession(false).removeAttribute("cart_session");// 删除提交唯一标识，防止重复提交
	}else{
		mv.addObject("op_title", "不能重复提交");
	}
	
	
	
	









