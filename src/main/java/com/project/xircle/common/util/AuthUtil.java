package com.project.xircle.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.xircle.model.User;

import java.util.Date;

public class AuthUtil {
    public static String getToken(User user)  {
        String jwtToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 60 * 60 * 24)))
                .withClaim("id", user.getId())
                .withClaim("email", user.getEmail())
                .sign(Algorithm.HMAC512("jwt sign value"));
        return jwtToken;
    }
}
