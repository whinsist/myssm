/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;

/**
 * 存取登录用户信息
 *
 * @author zharong
 */
public class AuthUtil {



    /**
     * 获取含有当前用户的Session对象
     *
     * @return Session
     * @author zharong
     */
    private static HttpSession getUserSession() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception ex) {
            return null;
        }
        return request.getSession();
    }



    public static Long getCurrentOrgSid() {
        AuthUser authUser = RequestContextUtil.getAuthUserInfo();
        if (authUser == null) {
            return null;
        }
        return authUser.getOrgSid();
    }
}
