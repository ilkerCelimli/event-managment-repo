package org.portifolyo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.portifolyo.requests.userservice.UserLoginRequest;

import java.util.Date;

public class JsonTokenUtils {

    private JsonTokenUtils() {
    }

    private static final Algorithm algorithm = Algorithm.HMAC256("MySuperSecretKey".getBytes());


    public static String generate(UserLoginRequest userLoginRequest, String[] roles, String id) {
        String token = JWT.create()
                .withClaim("email", userLoginRequest.email())
                .withArrayClaim("roles", roles)
                .withClaim("id", id)
                .withClaim("TYPE", "ACCESS")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 30)))
                .sign(algorithm);
        StringBuilder sb = new StringBuilder();
        return sb.append("Bearer ").append(token).toString();
    }

    public static String generateRefresh(String email, String[] roles, String id, String ip) {
        String token = JWT.create()
                .withClaim("email", email)
                .withClaim("id", id)
                .withExpiresAt(new Date(System.currentTimeMillis() + (180 * 60 * 60)))
                .withClaim("ip", ip)
                .withArrayClaim("roles", roles)
                .sign(algorithm);
        StringBuilder sb = new StringBuilder();
        return sb.append("Bearer ").append(token).toString();
    }

    public static <T> T extractClaim(String token, String name, Class<T> clazz) {
        return JWT.decode(token).getClaim(name).as(clazz);
    }

    public static DecodedJWT decodeJWT(String accessToken) {
        try {
            return JWT.decode(accessToken);
        } catch (Exception e) {
            return null;
        }
    }


}
