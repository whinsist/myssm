package cn.com.cloudstar.rightcloud.system.controller.common;

import cn.com.cloudstar.rightcloud.framework.common.constants.AuthConstants;
import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObject;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObjectUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.CookieUtils;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;
import cn.com.cloudstar.rightcloud.system.entity.system.User;
import cn.com.cloudstar.rightcloud.system.entity.system.UserToken;
import cn.com.cloudstar.rightcloud.system.pojo.beans.UserLoginBean;
import cn.com.cloudstar.rightcloud.system.service.system.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hong.Wu
 * @date: 8:12 2019/03/E24
 */
@RestController
@RequestMapping("/common")
public class UserLoginLogoutController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultObject login(@RequestBody UserLoginBean user, HttpServletRequest request, HttpServletResponse response) {
        UserToken token = userService.login(user);

        if (StringUtil.isNullOrEmpty(token.getUserSid())) {
            ResultObject resultObject = new ResultObject();
            resultObject.setCode(500);
            resultObject.setMessage("用户名或密码错误");
            if ("01".equals(token.getAccessToken())) {
                resultObject.setMessage("用户名或密码错误");
            } else if ("02".equals(token.getAccessToken())) {
                resultObject.setMessage("用户名或密码错误");
            } else if ("021".equals(token.getAccessToken())) {
                resultObject.setMessage("该账号正在审核中");
            } else if ("022".equals(token.getAccessToken())) {
                resultObject.setMessage("该账号已被禁用");
            } else if ("03".equals(token.getAccessToken())) {
                resultObject.setMessage("用户名或密码错误");
            } else if ("04".equals(token.getAccessToken())) {
                resultObject.setMessage("系统升级中");
            } else if ("05".equals(token.getAccessToken())) {
                resultObject.setMessage("该账号没有使用权限");
            }
            return resultObject;

        } else {
            RequestContextUtil.removeUserCache(user.getAccount());
            RequestContextUtil.cacheToken(user.getAccount(), token.getAccessToken());

            CookieUtils.setCookie(request, response, AuthConstants.LOGIN_COOKIE_NAME, token.getAccessToken());
            return ResultObjectUtil.success(token);
        }
    }


    @RequestMapping("/logout")
    @ResponseBody
    public ResultObject logout(HttpServletRequest request, HttpServletResponse response){
        CookieUtils.deleteCookie(request, response, AuthConstants.LOGIN_COOKIE_NAME);
        return ResultObjectUtil.success();
    }

    @RequestMapping("/current_user")
    @ResponseBody
    public ResultObject currentUser(HttpServletRequest request) {
        AuthUser authUserInfo = RequestContextUtil.getAuthUserInfo(request);
        User user = this.userService.selectByPrimaryKey(authUserInfo.getUserSid());
        return ResultObjectUtil.success(user);
    }



}
