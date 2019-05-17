package cn.com.cloudstar.rightcloud.framework.common.util.jwt;

import cn.com.cloudstar.rightcloud.framework.common.constants.AuthConstants;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 * @author Hong.Wu
 * @date: 22:23 2019/03/23
 */
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);


    public static String createJWT(String id, String issuer, String subject, long now, Long accessExpire) {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //We will sign our JWT with user secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(AuthConstants.SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(signatureAlgorithm, signingKey).setId(id).setIssuedAt(new Date(now))
                .setSubject(subject).setIssuer(issuer);

        //if it has been specified, let's add the expiration
        if (accessExpire >= 0) {
            Date exp = new Date(accessExpire);
            builder.setExpiration(exp);
        }
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }


    public static String parseJsonStrData(String token){
        Claims claims = Jwts.parser().setSigningKey(AuthConstants.SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    
    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        String token = JwtUtil.createJWT(uuid,
                "account", "12333",
                System.currentTimeMillis(),
                System.currentTimeMillis() + AuthConstants.TTL_MILLIS
        );
        System.out.println(parseJsonStrData(token));

        String str = parseJsonStrData("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5ZjZhMDlhYS02NzcxLTQyMGEtYTUyOS03NWJlZGFiMjJmMTciLCJpYXQiOjE1NTMzNDk3MTcsInN1YiI6IntcInVzZXJTaWRcIjo3NTIsXCJ1c2VyVHlwZVwiOlwiMDNcIixcInVzZXJDb2RlXCI6bnVsbCxcImFjY291bnRcIjpcIjAxMjJcIixcInBhc3N3b3JkXCI6XCIwZWNjNjQxM2QzM2EwYzdmZmMwNDkxNGZlODU1Zjg3OFwiLFwicGFzc3dvcmQ2NFwiOlwiTVhFeWR6TmxOSEk9XCIsXCJyZWFsTmFtZVwiOlwiMDEyMlwiLFwic2V4XCI6bnVsbCxcImVtYWlsXCI6XCIzMDYxNDExMjFAcXEuY29tXCIsXCJtb2JpbGVcIjpcIlwiLFwidGl0bGVcIjpudWxsLFwiY29tcGFueUlkXCI6NjY2NjcwODUsXCJwcm9qZWN0SWRcIjpudWxsLFwib3JnU2lkXCI6bnVsbCxcInN0YXR1c1wiOlwiMVwiLFwicmVtYXJrXCI6bnVsbCxcImVycm9yQ291bnRcIjowLFwibGFzdExvZ2luVGltZVwiOlwiMjAxOS0wMy0yMiAwOTo1NTowOVwiLFwibGFzdExvZ2luSXBcIjpcIjE5Mi4xNjguOTMuMVwiLFwic2VydmljZUxpbWl0UXVhbnRpdHlcIjpudWxsLFwiYXBwbHlSZWFzb25cIjpudWxsLFwic21zTWF4XCI6bnVsbCxcInV1aWRcIjpudWxsLFwic2tpblRoZW1lXCI6bnVsbCxcImF1dGhJZFwiOm51bGwsXCJhdXRoVHlwZVwiOlwibG9jYWxcIixcImNyZWF0ZWRCeVwiOlwiYWRtaW5cIixcImNyZWF0ZWREdFwiOlwiMjAxOS0wMS0yMSAxNDoyNzozMVwiLFwidXBkYXRlZEJ5XCI6XCJhZG1pblwiLFwidXBkYXRlZER0XCI6XCIyMDE5LTAzLTIyIDA5OjU1OjA5XCIsXCJ2ZXJzaW9uXCI6MX0iLCJpc3MiOiIwMTIyIiwiZXhwIjoxNTUzNDM2MTE3fQ.jK3Zl6f4J57i7c0FQJk6XrHQqOUq2We7jogMCOhGaGI");
        System.out.println(str);
    }   






}
