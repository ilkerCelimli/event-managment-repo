package com.example.gatewayserver.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.portifolyo.utils.JsonTokenUtils;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getPrincipal().toString();
        token = token.replace("Bearer ", "");
        String refreshToken = authentication.getCredentials().toString();
        try {
            DecodedJWT jwt = JsonTokenUtils.decodeJWT(token, refreshToken);
            String type = jwt.getClaim("TYPE").asString();

            if(type.equals("REFRESH")){
                token = JsonTokenUtils.generate(
                        jwt.getClaim("email").asString(),
                        jwt.getClaim("roles").asArray(String.class),
                        jwt.getClaim("id").asString(),
                        jwt.getClaim("ip").asString()
                );
            }

            String[] roles = jwt.getClaim("roles").asArray(String.class);
            List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
            Arrays.stream(roles).forEach(i -> authorityList.add(new SimpleGrantedAuthority(i)));
            String email = jwt.getClaim("email").asString();
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, token, authorityList);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return Mono.just(auth);
        } catch (Exception ex) {
            return Mono.just(authentication);
        }

    }
}
