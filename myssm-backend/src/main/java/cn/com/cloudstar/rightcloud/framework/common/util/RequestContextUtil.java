/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;


import cn.com.cloudstar.rightcloud.framework.common.cache.JedisUtil;
import cn.com.cloudstar.rightcloud.framework.common.constants.AuthConstants;
import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.util.jwt.JwtUtil;

import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * RequestContext工具类
 *
 * @author Chaohong.Mao
 */
public class RequestContextUtil {

    private static final Logger logger = LoggerFactory.getLogger(RequestContextUtil.class);

    private static final String USER_SQL =
            "SELECT\n" + "A.USER_SID,\n" + "A.USER_TYPE,\n" + "A.ACCOUNT,\n" + "A.REAL_NAME,\n" +
                    "A.SEX,\n" + "A.EMAIL,\n" + "A.MOBILE,\n" + "A.PROJECT_ID,\n" + "A.STATUS,\n" + "A.REMARK,\n" +
                    "A.LAST_LOGIN_IP,\n" + "A.UUID,\n" + "A.ORG_SID,\n" + "A.COMPANY_ID,\n" +
                    "B.CODE_DISPLAY AS USER_TYPE_NAME,\n" + "IF (SEX = 1, '女', '男') AS SEX_NAME,\n" +
                    " C.CODE_DISPLAY AS STATUS_NAME\n" + "FROM\n" + "  sys_m_user A\n" +
                    "LEFT JOIN sys_m_code B ON A.USER_TYPE = B.CODE_VALUE AND B.CODE_CATEGORY = 'USER_TYPE'\n" +
                    "LEFT JOIN sys_m_code C ON A. STATUS = C.CODE_VALUE AND C.CODE_CATEGORY = 'USER_STATUS'\n" +
                    "WHERE A.ACCOUNT = ?";

    private static final String COMPANY_USER_SQL =
            "SELECT\n" + "A.USER_SID,\n" + "A.USER_TYPE,\n" + "A.ACCOUNT,\n" + "A.REAL_NAME,\n" +
                    "A.SEX,\n" + "A.EMAIL,\n" + "A.MOBILE,\n" + "A.PROJECT_ID,\n" + "A.STATUS,\n" + "A.REMARK,\n" +
                    "A.LAST_LOGIN_IP,\n" + "A.UUID,\n" + "A.ORG_SID,\n" + "A.COMPANY_ID,\n" +
                    "D.COMPANY_NAME,\n" + "B.CODE_DISPLAY AS USER_TYPE_NAME,\n" +
                    "IF (SEX = 1, '女', '男') AS SEX_NAME,\n" + " C.CODE_DISPLAY AS STATUS_NAME\n" + "FROM\n" +
                    "  sys_m_user A\n" +
                    "LEFT JOIN sys_m_code B ON A.USER_TYPE = B.CODE_VALUE AND B.CODE_CATEGORY = 'USER_TYPE'\n" +
                    "LEFT JOIN sys_m_code C ON A. STATUS = C.CODE_VALUE AND C.CODE_CATEGORY = 'USER_STATUS'\n" +
                    "LEFT JOIN sys_m_org D ON D.ORG_TYPE = 'company' A.COMPANY_ID = D.ORG_SID\n"
                    + "WHERE A.ACCOUNT = ?";

    private static final String PLATFORM_USER_SQL =
            "SELECT\n" + "A.USER_SID,\n" + "A.USER_TYPE,\n" + "A.ACCOUNT,\n" + "A.REAL_NAME,\n" +
                    "A.SEX,\n" + "A.EMAIL,\n" + "A.MOBILE,\n" + "A.PROJECT_ID,\n" + "A.STATUS,\n" + "A.REMARK,\n" +
                    "A.LAST_LOGIN_IP,\n" + "A.UUID,\n" + "A.COMPANY_ID,\n" +
                    "B.CODE_DISPLAY AS USER_TYPE_NAME,\n" + "IF (SEX = 1, '女', '男') AS SEX_NAME,\n" +
                    "C.CODE_DISPLAY AS STATUS_NAME\n" + "FROM\n" +
                    "  sys_m_user A\n" +
                    "LEFT JOIN sys_m_code B ON A.USER_TYPE = B.CODE_VALUE AND B.CODE_CATEGORY = 'USER_TYPE'\n" +
                    "LEFT JOIN sys_m_code C ON A. STATUS = C.CODE_VALUE AND C.CODE_CATEGORY = 'USER_STATUS'\n" +
                    "WHERE A.ACCOUNT = ? AND A.USER_TYPE = ?";

    private static final String ROLE_SQL =
            "SELECT\n" + "  CAST(A.ROLE_SID AS CHAR(10)) AS ROLE_SID,\n" + "  A.ROLE_NAME,\n" + "  A.STATUS,\n" +
                    "  A.ROLE_TYPE\n" + "FROM sys_m_role A\n" + "  INNER JOIN sys_m_user_role B\n" +
                    "    ON A.ROLE_SID = B.ROLE_SID\n" + "WHERE\n" + "  B.USER_SID = ? ";
    private static final String PERMISIONS_SQL =
            "SELECT\n" + "  A.ROLE_SID,\n" + "  C.MODULE_SID\n" + "FROM sys_m_user_role A\n" +
                    "  INNER JOIN sys_m_role B\n" + "    ON A.ROLE_SID = B.ROLE_SID\n" +
                    "  INNER JOIN sys_m_role_module C ON C.ROLE_SID = A.ROLE_SID\n" + "WHERE A.USER_SID = ? \n" +
                    "GROUP BY C.MODULE_SID";
    private static final String PERMISIONS_BY_ROLE_SQL =
            "SELECT\n" + "  A.ROLE_SID,\n" + "  B.MODULE_SID\n" + "FROM sys_m_role A\n" +
                    "  INNER JOIN sys_m_role_module B ON B.ROLE_SID = A.ROLE_SID\n" + "WHERE A.ROLE_SID = ?\n" +
                    "GROUP BY B.MODULE_SID";
    private static final String USER_BASE_INFO = "SELECT * FROM sys_m_user where ACCOUNT = ?";

    private static final String TOKEN_PREFIX = "^Bearer$";

    /**
     * ResCommonInst 中的属性
     */
    private static final String FIELD_MGT_OBJ_SID = "mgtObjSid";
    private static final String FIELD_USER_ACCOUNT = "userAccount";
    private static final String FIELD_USER_PASS = "userPass";
    private static final String FIELD_ZONE_ID = "zoneId";
    private static final String FIELD_RES_SPEC_PARAM_ = "resSpecParam";

    /**
     * 创建基础的服务类参数.
     *
     * @param <T> the type parameter
     * @param req HttpRequest
     * @param resBaseInfoClazz 资源层共通参数类
     *
     * @return the t
     */
    public static <T> T buildResBaseInfo(HttpServletRequest req, Class<T> resBaseInfoClazz) {
        return buildResBaseInfo(req, resBaseInfoClazz, false);
    }

    /**
     * 创建基础的服务类参数.
     *
     * @param <T> the type parameter
     * @param req HttpServletRequest
     * @param resBaseInfoClazz 资源层共通参数类
     * @param withJsonParam 是否设置JSON配置项<br/> true -> 从request的body中取得参数，直接设置到对象的字段上，但是有前提是REST接口上面， 没有使用自动映射参数
     *
     * @return the t
     *
     * @throws IllegalStateException 如果 {@code withJsonParam} 为true，发生异常由于 {@link
     *         HttpServletRequest#getInputStream} 或者 {@link HttpServletRequest#getReader()} 在这个Request上已经被调用过
     */
    public static <T> T buildResBaseInfo(HttpServletRequest req, Class<T> resBaseInfoClazz, boolean withJsonParam) {
        AuthUser authUser = getAuthUserInfo(req);
        T result = transUserInfo2ResBase(authUser, resBaseInfoClazz);
        // 将request的body（JSON）设置到对象上
        if (withJsonParam) {
            String body = null;
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = req.getReader();
                while ((body = reader.readLine()) != null) {
                    sb.append(body);
                }
                // resSpecParam
                Field resSpecParam = ReflectionUtils.findField(resBaseInfoClazz, FIELD_RES_SPEC_PARAM_);
                ReflectionUtils.makeAccessible(resSpecParam);
                ReflectionUtils.setField(resSpecParam, result, sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 获得授权用户
     *
     * @return the auth user info
     */
    public static AuthUser getAuthUserInfo() {
        return getAuthUserInfo(WebUtil.getRequest());
    }

    /**
     * 获得授权用户
     *
     * @param req the req
     *
     * @return the auth user info
     */
    public static AuthUser getAuthUserInfo(HttpServletRequest req) {

        if (true) {
            String token = getToken(req);
            return JsonUtil.fromJson(JwtUtil.parseJsonStrData(token), AuthUser.class);
        }

        Claims claims = (Claims) req.getAttribute(AuthConstants.CLAIMS_KEY);
        if (claims == null) {
            String token = getToken(req);
            if (token == null) {
                return null;
            }
            try {
                claims = Jwts.parser().setSigningKey(AuthConstants.SECRET_KEY).parseClaimsJws(token).getBody();
                req.setAttribute(AuthConstants.CLAIMS_KEY, claims);
            } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
                throw e;
            } catch (Exception e) {
                logger.error(Throwables.getStackTraceAsString(e));
                return null;
            }
            if (claims == null) {
                return null;
            }
        }

        AuthUser authUser = JsonUtil.fromJson(claims.getSubject(), AuthUser.class);
        // 可缓存到redis

        return authUser;
    }

    /**
     * 创建基础的服务类参数.
     *
     * @param <T> the type parameter
     * @param authUser 授权用户
     * @param resBaseInfoClazz 资源层参数类
     *
     * @return the t
     */
    public static <T> T transUserInfo2ResBase(AuthUser authUser, Class<T> resBaseInfoClazz) {
        T result;
        try {
            result = resBaseInfoClazz.newInstance();
            // mgt_obj_sid
            Field mgtObjSid = ReflectionUtils.findField(resBaseInfoClazz, FIELD_MGT_OBJ_SID);
            ReflectionUtils.makeAccessible(mgtObjSid);
            ReflectionUtils.setField(mgtObjSid, result, authUser.getMgtObjSid());
            // userAccount
            Field userAccount = ReflectionUtils.findField(resBaseInfoClazz, FIELD_USER_ACCOUNT);
            ReflectionUtils.makeAccessible(userAccount);
            ReflectionUtils.setField(userAccount, result, authUser.getAccount());
            // userPass
            Field userPass = ReflectionUtils.findField(resBaseInfoClazz, FIELD_USER_PASS);
            ReflectionUtils.makeAccessible(userPass);
            ReflectionUtils.setField(userPass, result, authUser.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    /**
     * 获得 token.
     *
     * @param httpRequest the http request
     *
     * @return the token
     */
    public static String getToken(HttpServletRequest httpRequest) {
        String token = null;
        final String authorizationHeader = httpRequest.getHeader("authorization");
        if (authorizationHeader == null) {
            return getCookieToken(httpRequest);
        }

        if (authorizationHeader == null) {
            throw new AuthenticationException("Unauthorized: No Authorization header was found");
        }

        String[] parts = authorizationHeader.split(" ");

        if (parts.length != 2) {
            throw new AuthenticationException("Unauthorized: Format is Authorization: Bearer [token]");
        }

        String scheme = parts[0];
        String credentials = parts[1];

        Pattern pattern = Pattern.compile(TOKEN_PREFIX, Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(scheme).matches()) {
            token = credentials;
        }
        return token;
    }


    public static String getCookieToken(HttpServletRequest httpRequest) {
        Cookie[] cookies = httpRequest.getCookies();
        if (Objects.isNull(cookies)) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("PLATFORM_CMP_TOKEN")) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 获取授权用户信息
     *
     * @param userAccount 用户account
     *
     * @return the auth user
     */
    private static AuthUser getAuthUserInfo(String userAccount, String userType, String orgSid) {
        AuthUser authUser;
        // Redis中
        Map<String, String> userInfo = JedisUtil.instance().hgetall(AuthConstants.CACHE_KEY_USER_PREFIX + userAccount);
        if (userInfo != null && userInfo.size() > 0) {
            userInfo.put("orgSid", orgSid);
            authUser = JsonUtil.fromJson(JsonUtil.toJson(userInfo), AuthUser.class);
        } else {
            // From DB
            Map dbUserMap = null;
            dbUserMap = DBUtil.queryMap(PLATFORM_USER_SQL, userAccount, userType);
            Map<String, String> userMap = new HashMap<>();
            for (Object column : dbUserMap.keySet()) {
                String key = column.toString();
                String camelKey = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
                userMap.put(camelKey, StringUtil.nullToEmpty(dbUserMap.get(key)));
            }
            userMap.put("orgSid", orgSid);
            authUser = JsonUtil.fromJson(JsonUtil.toJson(userMap), AuthUser.class);
            JedisUtil.instance()
                     .hmset(AuthConstants.CACHE_KEY_USER_PREFIX + userAccount, userMap, AuthConstants.PERIED_TIME);
        }
        return authUser;
    }

    public static Long getUserIdFromAccount(String account) {
        String userSid = JedisUtil.instance().hget(AuthConstants.CACHE_KEY_ACCOUNT_PREFIX, account);
        if (Strings.isNullOrEmpty(userSid)) {
            Map userInfoMap = DBUtil.queryMap(USER_BASE_INFO, account);
            userSid = userInfoMap.get("USER_SID").toString();
            JedisUtil.instance().hset(AuthConstants.CACHE_KEY_ACCOUNT_PREFIX, account, userSid, 60 * 60 * 24);
        }

        return Long.parseLong(userSid);
    }

    /**
     * 获取授权用户信息
     *
     * @param userAccount 用户account
     *
     * @return the auth user
     */
    public static AuthUser getAuthUserInfo(String userAccount) {
        // Redis中
        Map<String, String> userInfo = JedisUtil.instance().hgetall(AuthConstants.CACHE_KEY_USER_PREFIX + userAccount);
        if (userInfo != null && userInfo.size() > 0) {
            return JsonUtil.fromJson(JsonUtil.toJson(userInfo), AuthUser.class);
        }

        Map dbUserMap = DBUtil.queryMap(USER_SQL, userAccount);
        Map<String, String> userMap = new HashMap<>(16);
        for (Object column : dbUserMap.keySet()) {
            String key = column.toString();
            String camelKey = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
            userMap.put(camelKey, StringUtil.nullToEmpty(dbUserMap.get(key)));
        }

        AuthUser authUser = JsonUtil.fromJson(JsonUtil.toJson(userMap), AuthUser.class);
        JedisUtil.instance()
                 .hmset(AuthConstants.CACHE_KEY_USER_PREFIX + userAccount, userMap, AuthConstants.PERIED_TIME);

        return authUser;
    }

    public static String getCurrentUserName() {
        if (SecurityUtils.getSubject().getPrincipal() != null) {
            return SecurityUtils.getSubject().getPrincipal().toString();
        } else {
            return null;
        }
    }

    public static boolean removeUserCache(String userAccount) {
        JedisUtil.instance().del(AuthConstants.CACHE_KEY_USER_PREFIX + userAccount);
        return true;
    }

    /**
     * cache user token
     */
    public static void cacheToken(String account, String token) {
        JedisUtil.instance().set(AuthConstants.CACHE_USER_TOKEN + account, token);
    }

    /**
     * clear cookie for platform's token
     *
     * @param request
     * @param response
     */
    public static void clearPlatformTokenCookie(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("PLATFORM_IDC_TOKEN".equalsIgnoreCase(cookie.getName())) {
                    Cookie nilCookie = new Cookie(cookie.getName(), null);
                    nilCookie.setValue(null);
                    if (!Strings.isNullOrEmpty(cookie.getDomain())) {
                        nilCookie.setDomain(cookie.getDomain());
                    }
                    nilCookie.setMaxAge(0);
                    nilCookie.setPath("/");
                    response.addCookie(nilCookie);
                }
            }
        }
    }


    /**
     * get cookie value by name
     *
     * @param request
     * @param cookieName
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * delete cookie by cookieName
     *
     * @param request
     * @param response
     * @param cookieName
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    Cookie delCookie = new Cookie(cookie.getName(), null);
                    delCookie.setValue(null);
                    if (StringUtil.isNotBlank(cookie.getDomain())) {
                        delCookie.setDomain(cookie.getDomain());
                    }
                    delCookie.setMaxAge(0);
                    delCookie.setPath("/");
                    response.addCookie(delCookie);
                }
            }
        }
    }

    public static Long getCurrentUserId() {
        return getUserIdFromAccount(getCurrentUserName());
    }
}
