/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.framework.shiro;

import cn.com.cloudstar.rightcloud.framework.common.cache.JedisUtil;
import cn.com.cloudstar.rightcloud.framework.common.constants.AuthConstants;
import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.util.DBUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * Created by qct on 2016/4/26.
 *
 * @author qct
 */
public class StatelessRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(StatelessRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        logger.debug(username);
        AuthUser authUserInfo = RequestContextUtil.getAuthUserInfo(username);
        if (authUserInfo != null) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRoles(Lists.newArrayList(JsonUtil.fromJson(authUserInfo.getRoles(), String[].class)));
            authorizationInfo.addStringPermissions(Lists.newArrayList(JsonUtil.fromJson(authUserInfo.getPermissions(),
                    String[].class
            )));
            return authorizationInfo;
        } else {
            throw new IncorrectCredentialsException();
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (!StatelessToken.class.isInstance(token)) {
            return null;
        }
        StatelessToken statelessToken = StatelessToken.class.cast(token);

        if (Strings.isNullOrEmpty(statelessToken.getToken()) || "undefined".equals(statelessToken.getToken())) {
            logger.debug("user({})'s token({}) is valid.", statelessToken.getUsername(), statelessToken.getToken());
            return null;
        }

        final Claims claims = Jwts.parser().setSigningKey(AuthConstants.SECRET_KEY)
                .parseClaimsJws(statelessToken.getToken()).getBody();

        //1.从缓存或者数据库中查詢token是否存在
        if (statelessToken.getToken().equals(JedisUtil.instance().get(AuthConstants.CACHE_USER_TOKEN +
                claims.getIssuer()))) {// cache hit
            return getAuthenticationInfo(statelessToken);
        } else {
            Map accessTokens = DBUtil
                    .queryMap("select access_token from sys_m_user_token where uuid='" + claims.getId() + "'");
            if (accessTokens != null && accessTokens.size() != 1) {
                logger.debug("user({})'s token({}) is valid.", statelessToken.getUsername(), statelessToken.getToken());
                return null;
            }
            JedisUtil.instance().set(AuthConstants.CACHE_USER_TOKEN + claims.getIssuer(),
                    accessTokens.get("access_token").toString()
            );
            JedisUtil.instance().expire(AuthConstants.CACHE_USER_TOKEN + claims.getIssuer(), 60 * 60);
            return getAuthenticationInfo(statelessToken);
        }
    }

    private AuthenticationInfo getAuthenticationInfo(StatelessToken statelessToken) {
        //2.如果token存在，拿出token信息验证過期時間等
        final Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(AuthConstants.SECRET_KEY).parseClaimsJws(statelessToken.getToken())
                    .getBody();
        } catch (ExpiredJwtException e) {
            logger.debug("ExpiredJwtException occurred when parsing jwt token.{}", e.getMessage());
            e.printStackTrace();
            return null;
        }
        if (claims.getExpiration().before(new Date())) {
            logger.debug("user({})'s token({}) expired.", statelessToken.getUsername(), statelessToken.getToken());
            return null;
        }
        return new SimpleAuthenticationInfo(statelessToken.getUsername(), statelessToken.getToken(), getName());
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        String account = (String) principals.getPrimaryPrincipal();
        logger.debug("user log out - {}", account);
        // delete user token
        // from cache
        JedisUtil.instance().del(AuthConstants.CACHE_USER_TOKEN + account);
        // from DB
        DBUtil.update(
                "DELETE FROM sys_m_user_token WHERE user_sid = (SELECT user_sid FROM sys_m_user WHERE account = ?)",
                account
        );
        super.onLogout(principals);
    }
}
