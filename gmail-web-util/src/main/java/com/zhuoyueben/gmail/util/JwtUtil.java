package com.zhuoyueben.gmail.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-06-01 20:49
 */
public class JwtUtil {
    public static String encode(String key, Map<String,Object> param,String salt){
        if (salt!=null){
            key+=salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256, key);
        jwtBuilder.setClaims(param);
        String token = jwtBuilder.compact();
        return token;
    }
    public static Map<String,Object> decode(String key, String token,String salt){
        Claims claims=null;
        Map<String,Object> map=new HashMap<>();
        if (salt!=null){
            key+=salt;
        }
        try {
            claims= Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        }catch (Exception e){
            map.put("key","fail");
            return map;
        }

        return claims;
    }
}
