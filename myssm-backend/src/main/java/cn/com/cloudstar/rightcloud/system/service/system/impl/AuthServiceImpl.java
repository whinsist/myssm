/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.service.system.impl;

import cn.com.cloudstar.rightcloud.framework.common.constants.AuthConstants;
import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.jwt.JwtUtil;
import cn.com.cloudstar.rightcloud.system.entity.system.User;
import cn.com.cloudstar.rightcloud.system.entity.system.UserToken;
import cn.com.cloudstar.rightcloud.system.service.system.AuthService;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 * AuthServiceImpl
 *
 * @author qct
 * @date 2016/2/3.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String createJWT(String id, String issuer, String subject, long ttlMillis) {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with user secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(AuthConstants.SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                                 .signWith(signatureAlgorithm, signingKey).setId(id).setIssuedAt(now)
                                 .setSubject(subject).setIssuer(issuer);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    @Override
    public UserToken createJWT(User user) {
        long accessExpire = 0L;
        if (AuthConstants.TTL_MILLIS >= 0) {
            accessExpire = System.currentTimeMillis() + AuthConstants.TTL_MILLIS;
        }
        if (user.getOrgSid() == null) {
            user.setOrgSid(1111111L);
        }
        String uuid = UUID.randomUUID().toString();
        String subject = JsonUtil.toJson(user);
        String token = JwtUtil.createJWT(uuid,
                                         user.getAccount(),
                                         subject,
                                         System.currentTimeMillis(),
                                         accessExpire
        );
        UserToken userToken = new UserToken();
        userToken.setUserSid(user.getUserSid());
        userToken.setUuid(uuid);
        userToken.setAccessExpire(accessExpire);
        userToken.setAccessToken(token);
        return userToken;
    }


    @Override
    public boolean verifyToken(String token) {

        return true;
    }
}