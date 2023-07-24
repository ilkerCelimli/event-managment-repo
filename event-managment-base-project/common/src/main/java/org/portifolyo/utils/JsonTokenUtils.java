package org.portifolyo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.portifolyo.requests.userservice.UserLoginRequest;

import java.util.Date;

public class JsonTokenUtils {


    private static final Algorithm algorithm = Algorithm.HMAC256("MySuperSecretKey".getBytes());



    public static String generate(UserLoginRequest userLoginRequest){
        return JWT.create()
                .withClaim("email",userLoginRequest.email())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000*60*30)))
                .sign(algorithm);
    }

    public static String generate(String email){
        return JWT.create()
                .withClaim("email",email)
                .withExpiresAt(new Date(System.currentTimeMillis() + (30*60*60)))
                .sign(algorithm);
    }

    public static DecodedJWT validate(String token){
        return JWT.require(algorithm).build().verify(token);
    }



}
