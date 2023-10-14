package org.portifolyo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.portifolyo.requests.userservice.UserLoginRequest;

import java.util.Date;

public class JsonTokenUtils {

    private JsonTokenUtils(){}

    private static final Algorithm algorithm = Algorithm.HMAC256("MySuperSecretKey".getBytes());



    public static String generate(UserLoginRequest userLoginRequest,String[] roles,String id){
        return JWT.create()
                .withClaim("email",userLoginRequest.email())
                .withArrayClaim("roles",roles)
                .withClaim("id",id)
                .withClaim("TYPE","ACCESS")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000*60*30)))
                .sign(algorithm);
    }

    public static String generate(String email,String[] roles,String id,String ip){
        return JWT.create()
                .withClaim("email",email)
                .withClaim("id",id)
                .withExpiresAt(new Date(System.currentTimeMillis() + (30*60*60)))
                .withClaim("ip",ip)
                .withClaim("TYPE","REFRESH")
                .withArrayClaim("roles",roles)
                .sign(algorithm);
    }

    public static String generateRefresh(String email,String[] roles,String id , String ip){
        return JWT.create()
                .withClaim("email",email)
                .withClaim("id",id)
                .withExpiresAt(new Date(System.currentTimeMillis() + (180*60*60)))
                .withClaim("ip",ip)
                .withArrayClaim("roles",roles)
                .sign(algorithm);
    }

    public static <T> T extractClaim(String token,String name , Class<T> clazz){
        return JWT.decode(token).getClaim(name).as(clazz);
    }

    public static DecodedJWT decodeJWT(String accessToken,String refreshToken){
        try {
            return JWT.decode(accessToken);
        }

        catch (Exception e){
            return JWT.decode(refreshToken);
        }
    }



}
