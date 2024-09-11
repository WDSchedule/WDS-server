package com.WDS;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {

    @Test
    public void testGen(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "zhangsan");
        //生成JWT代码
        String token = JWT.create()
                .withClaim("user", claims) //添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))
                .sign(Algorithm.HMAC256("test"));//配置密钥
        System.out.println(token);
    }

    @Test
    public void testParse(){
        //定义字符串，模拟用户token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7InVzZXJuYW1lIjoiemhhbmdzYW4ifSwiZXhwIjoxNzI2MTA4MjYzfQ." +
                "H80YGL-UfVmoQ-Uu_FMLaKMMvqJtvxEkM93KrUdnbRQ";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("test")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}