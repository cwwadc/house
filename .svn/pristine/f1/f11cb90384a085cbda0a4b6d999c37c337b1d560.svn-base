package com.msz.util;

import com.msz.util.config.JwtConsts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lzm on 18/12/14.
 * 用于jwt生成和校验的工具包
 */
public class JwtUtil {
    private JwtUtil() {

    }

    /**
     * 得到一个jwt
     *
     * @param subject jwt 面向的用户
     * @return
     */
    public static String createJWT(String subject) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JwtConsts.SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(JwtConsts.ISSUER)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (JwtConsts.TTLMILLIS >= 0) {
            long expMillis = nowMillis + JwtConsts.TTLMILLIS;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * 验证jwt.
     *
     * @param jwt
     */
    @SuppressWarnings("unused")
	public static int parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(JwtConsts.SECRET))
                    .parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

}
