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
        String token = authentication.getCredentials().toString();
        token = token.replace("Bearer ","");
        DecodedJWT jwt = JsonTokenUtils.validate(token);
        String[] roles = jwt.getClaim("roles").asArray(String.class);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        Arrays.stream(roles).forEach(i -> authorityList.add(new SimpleGrantedAuthority(i)));
        String email = jwt.getClaim("email").asString();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email,token,authorityList);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return Mono.just(auth);
    }
}
