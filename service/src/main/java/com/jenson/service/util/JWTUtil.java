package com.jenson.service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jenson.service.common.dto.user.vo.UserVo;

import java.util.Calendar;
import java.util.HashMap;

public class JWTUtil {

    private static final String secretKey = "jenson";

    /**
     * 获取token
     * @param userVo 用户
     * @return token
     */
    public static String getToken(UserVo userVo) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 2);

        return JWT.create()
                .withClaim("userName", userVo.getName())
                .sign(Algorithm.HMAC256(secretKey));
    }

    /**
     * 通过token查询用户
     * @param token token
     * @return HashMap(string,string)
     */
    public static HashMap<String, String> getContext(String token) {
        HashMap<String, String> hashMap = new HashMap<>();
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token);
        hashMap.put("userName", verify.getClaim("userName").asString());
        return hashMap;

    }

}
