package org.example.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.constant.LoginConstant;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    // 自己设定的密钥
    private static final String SECRET ="1243%fjd$%a!ida";
    /*
    功能：生成JWT令牌
    荷载payload存储信息userid
     */
    public static String createToken(Long userId) {
        //生成JWT的荷载信息，这里我将userid放进去，可以根据需要放所需信息
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .setIssuedAt(new java.util.Date())//设置签发时间
                .setExpiration(new Date(System.currentTimeMillis()+ LoginConstant.JWT_EXPIRE_TIME));//设置有效期
        return jwtBuilder.compact();
    }

    /*
    检查token合法性
     */
    public static Map<String,Object> checkToken(String token) {
        try {
            Jwt parse = Jwts.parser().setSigningKey(SECRET).parse(token);
            return (Map<String, Object>) parse.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    测试生成token是否成功和验证token合法性是否成功
     */
    public static void main(String[] args){
        String token = createToken(1L);
        System.out.println(token);
        Map<String, Object> map = checkToken(token);
        System.out.println(map.get("userId"));
    }
}
