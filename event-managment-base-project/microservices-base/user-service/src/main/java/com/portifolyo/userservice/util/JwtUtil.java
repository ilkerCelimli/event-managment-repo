package com.portifolyo.userservice.util;

import com.auth0.jwt.HeaderParams;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Header;
import org.portifolyo.requests.userservice.UserLoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final Algorithm algorithm;

    public JwtUtil(@Value("${jwt.secret-key}") String secreyKey) {

        this.algorithm = Algorithm.HMAC256(secreyKey.getBytes());
    }

    public String generate(UserLoginRequest userLoginRequest){
        return JWT.create()
                .withClaim("email",userLoginRequest.email())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000*60*30)))
                .sign(algorithm);
    }

    public String generate(String email){
        return JWT.create()
                .withClaim("email",email)
                .withExpiresAt(new Date(System.currentTimeMillis() + (30*60*60)))
                .sign(algorithm);
    }

    public DecodedJWT validate(String token){
       return JWT.require(algorithm).build().verify(token);
    }



}
