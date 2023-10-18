package com.example.gatewayserver.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.gatewayserver.model.SecurityModel;
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
import java.util.Objects;

@Component
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        SecurityModel model = (SecurityModel) authentication.getPrincipal();
        if (model != null) {
                if(Objects.isNull(model.getAccessToken())){
                    String newAccessToken = JsonTokenUtils.generateRefresh(model.getEmail(),model.getRoles().toArray(String[]::new),model.getId(),model.getIp());
                    model.setAccessToken(newAccessToken);
                }
                String[] roles = new String[model.getRoles().size()];
                roles = model.getRoles().toArray(roles);
                List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
                Arrays.stream(roles).forEach(i -> authorityList.add(new SimpleGrantedAuthority(i)));
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(model,model,authorityList);
                SecurityContextHolder.getContext().setAuthentication(auth);
                return Mono.just(auth);

        }
        return Mono.just(authentication);
    }
}
