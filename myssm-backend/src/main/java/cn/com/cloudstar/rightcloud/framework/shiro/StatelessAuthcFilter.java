/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.framework.shiro;


import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.pojo.HttpServletResponseExtend;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObject;
import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import cn.com.cloudstar.rightcloud.system.service.system.SysConfigService;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qct on 2016/4/26.
 *
 * @author qct
 */
public class StatelessAuthcFilter extends AccessControlFilter {

    private static final Logger logger = LoggerFactory.getLogger(StatelessAuthcFilter.class);

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        String method = ((HttpServletRequest) request).getMethod();
        //解决跨域时候中的权限问题，一旦有跨域，会先发options请求来询问是否允许跨域，此时应该放这个options请求通过
        if ("options".equals(method.toLowerCase())) {
            logger.info("it's cross-origin preflight request, must make it through");
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("request uri: {}", ShiroHttpServletRequest.class.cast(request).getRequestURI());

        Map<String, String[]> params = new HashMap<>(request.getParameterMap());

        try {
            //4、生成无状态Token
            AuthUser authUserInfo = RequestContextUtil.getAuthUserInfo(HttpServletRequest.class.cast(request));
            if (authUserInfo == null) {
                logger.info("Unauthorized: Cannot found Authorization user info.");
                //6、登录失败
                onLoginFail(response);
                return false;
            }
            //系统升级时判断该用户是否允许访问
            boolean hasAccess = sysConfigService.querySysUpgradeUserFlag(authUserInfo.getUserSid());
            if (!hasAccess) {
                logger.info("System upgrade Unauthorized userSid={}", authUserInfo.getUserSid());
                onLoginFailSysUpgrade(request, response);
                return false;
            }
            StatelessToken token = new StatelessToken(authUserInfo.getAccount(),
                    params,
                    RequestContextUtil.getToken(HttpServletRequest.class.cast(request))
            );
            //5、委托给Realm进行登录
            getSubject(request, response).login(token);
        } catch (Exception e) {
            logger.error("拦截失败：", e);
            //6、登录失败
            //onLoginFail(response);
            ResultObject resultObject = new ResultObject();
            resultObject.setCode(4001);
            resultObject.setMessage("拦截失败："+e.getMessage());
            WebUtil.renderJson(HttpServletResponse.class.cast(response), JsonUtil.toJson(resultObject));
            return false;
        }

        return true;
    }

    /**
     * 登录失败时默认返回401状态码
     */
    private void onLoginFail(ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        throw new BizException("Unauthorized: Cannot found Authorization user info.");
    }

    /**
     * 系统升级用户无权限访问
     */
    private void onLoginFailSysUpgrade(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = HttpServletRequest.class.cast(request);
        httpResponse.setStatus(HttpServletResponseExtend.SC_UNAUTHORIZED_SYSUPGRADE);
        RequestContextUtil.clearPlatformTokenCookie(httpRequest, httpResponse);
    }

    /**
     * 平台使用过期
     */
    private void onPlatformExpire(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        HttpServletRequest httpRequest = HttpServletRequest.class.cast(request);
        RequestContextUtil.clearPlatformTokenCookie(httpRequest, httpResponse);
    }


}
