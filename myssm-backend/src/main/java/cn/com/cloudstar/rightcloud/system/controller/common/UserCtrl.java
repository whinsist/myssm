/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.controller.common;

import cn.com.cloudstar.rightcloud.framework.common.api.ApiGroup;
import cn.com.cloudstar.rightcloud.framework.common.api.ApiGroupEnum;
import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;
import cn.com.cloudstar.rightcloud.system.entity.system.User;
import cn.com.cloudstar.rightcloud.system.entity.system.UserToken;
import cn.com.cloudstar.rightcloud.system.pojo.beans.UserLoginBean;
import cn.com.cloudstar.rightcloud.system.service.system.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ApiGroup(ApiGroupEnum.OPERATION_GROUP)
@Api(description = "用户Ctrl",value = "/users")
@RestController
@RequestMapping("/users")
public class UserCtrl {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public RestResult get() {
        List<User> users = this.userService.selectByParams(null);
        return new RestResult(users);
    }

    @GetMapping("/currentUser")
    public RestResult getCurrentUser(HttpServletRequest request){
        String currentUserName = RequestContextUtil.getCurrentUserName();
        AuthUser authUserInfo = RequestContextUtil.getAuthUserInfo(request);

        Map<String, Object> map = new HashMap<>();
        map.put("currentUserName", currentUserName);
        map.put("authUserInfo", authUserInfo);
        return new RestResult(map);
    }

    @ApiOperation(httpMethod = "POST",value = " 用户验证登陆",notes = "通过用户user实体对象，数据用json数据格式提交")
    @PostMapping("/login")
    public RestResult login(@RequestBody UserLoginBean user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        // 查询用户
        UserToken token = userService.login(user);
        if (StringUtil.isNullOrEmpty(token.getUserSid())) {
            resultMap.put("resultStatus", "false");
            if ("01".equals(token.getAccessToken())) {
                resultMap.put("data", "用户名或密码错误");
            } else if ("02".equals(token.getAccessToken())) {
                resultMap.put("data", "用户名或密码错误");
            } else if ("021".equals(token.getAccessToken())) {
                resultMap.put("data", "该账号正在审核中");
            } else if ("022".equals(token.getAccessToken())) {
                resultMap.put("data", "该账号已被禁用");
            } else if ("03".equals(token.getAccessToken())) {
                resultMap.put("data", "用户名或密码错误");
            } else if ("04".equals(token.getAccessToken())) {
                resultMap.put("data", "系统升级中");
            } else if ("05".equals(token.getAccessToken())) {
                resultMap.put("data", "该账号没有使用权限");
            } else {
                resultMap.put("data", "用户名或密码错误");
            }
        } else {
            resultMap.put("resultStatus", "true");
            resultMap.put("data", token);
            RequestContextUtil.removeUserCache(user.getAccount());
            RequestContextUtil.cacheToken(user.getAccount(), token.getAccessToken());


        }
        return new RestResult(resultMap);
    }

}
