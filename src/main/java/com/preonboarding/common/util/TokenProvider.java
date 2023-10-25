package com.preonboarding.common.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class TokenProvider {

    public static String getUserName(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
                .getBody().get("userName", String.class);
    }

    public static boolean validationToken(String token, String secretKey) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error(e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error(e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public static String createJwt(String userName, String secretKey, Long expiredMs) {

        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
