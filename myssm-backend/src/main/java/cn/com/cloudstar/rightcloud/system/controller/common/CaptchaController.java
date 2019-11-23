package cn.com.cloudstar.rightcloud.system.controller.common;

import cn.com.cloudstar.rightcloud.framework.common.cache.JedisUtil;
import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult;
import cn.com.cloudstar.rightcloud.framework.common.util.ValidateCode;
import cn.com.cloudstar.rightcloud.system.service.system.UserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/captcha")
public class CaptchaController {

	@Autowired
	private UserService userService;

	@RequestMapping("/info")
	@ResponseBody
	public RestResult getCaptcha() {
		ValidateCode vCode = new ValidateCode(100, 30, 4, 10);
		String captcha = vCode.getCode();
		byte[] buffImgByte = vCode.getBuffImgByte();

		String captchaBase64Str = "data:image/jpg;base64,"+ Base64.encodeBase64String(buffImgByte);
		String captchaKey = "captchaKey_"+ UUID.randomUUID();
		JedisUtil.instance().set(captchaKey, captcha);

		Map<String, Object> map = new HashMap<>(1);
		map.put("captchaBase64Str", captchaBase64Str);
		map.put("captchaKey", captchaKey);
		return new RestResult(map);
	}





 
}
