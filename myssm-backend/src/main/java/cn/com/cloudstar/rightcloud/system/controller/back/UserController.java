/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.controller.back;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cloudstar.rightcloud.framework.common.pojo.BaseGridReturn;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObject;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObjectUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import cn.com.cloudstar.rightcloud.system.pojo.User;
import cn.com.cloudstar.rightcloud.system.pojo.jqgrid.JQGridBean;
import cn.com.cloudstar.rightcloud.system.pojo.jqgrid.JQGridBeanUtil;
import cn.com.cloudstar.rightcloud.system.service.UserService;
import cn.com.cloudstar.rightcloud.system.vo.UserEditVo;


@Controller
@RequestMapping("/back/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    @PostMapping("/list")
    @ResponseBody
    public JQGridBean list(HttpServletRequest request) {
        Criteria criteria = new Criteria();
        WebUtil.preparePageParams(request, criteria, "user_sid asc");
        List<User> users = this.userService.selectByParams(criteria);
        return JQGridBeanUtil.buildJQGrid(users);
    }


    @PostMapping("/add")
    @ResponseBody
    public ResultObject addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResultObjectUtil.success();
    }



    @PostMapping("/edit_profile")
    @ResponseBody
    public ResultObject editProfile(@RequestBody User user) {
        this.userService.updateUser(user);
        return ResultObjectUtil.success(user);
    }



    @RequestMapping("/deleteOrUpdate")
    @ResponseBody
    public ResultObject editProfile(UserEditVo userEditVo) {
        userService.deleteOrUpdateUser(userEditVo);
        return ResultObjectUtil.success();
    }




}
