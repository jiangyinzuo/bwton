package pers.jiangyinzuo.carbon.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

/**
 * @author Jiang Yinzuo
 */
public class TokenUtil {

    public static String generateToken(TokenDetail tokenDetail) {
        Map<String, Object> claims = new HashMap<>(4);
        claims.put("userName", tokenDetail.getUserName());
        claims.put("createTime", new Date(System.currentTimeMillis()));
        return generateToken(claims);
    }

    public static String generateToken(Map<String, Object> claims) {
        String secret = "random";
        final long expirationTime = 1000000L;
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 100000))
                .signWith(HS256, secret)
                .compact();
    }

    public static String getUserNameFromJwt(String token) {
        String secret = "random";
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.toString();
    }

    public static void main(String[] args) {
        String token = TokenUtil.generateToken(new TokenDetail("jyz"));
        System.out.println(token);
        System.out.println(TokenUtil.getUserNameFromJwt(token));
    }
}
