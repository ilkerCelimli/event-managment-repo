package com.portifolyo.ticketservice.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.portifolyo.utils.JsonTokenUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header != null) {
            header = header.replace("Bearer ","");
            DecodedJWT jwt = JsonTokenUtils.validate(header);
            String email = jwt.getClaim("email").asString();
            String[] roles = jwt.getClaim("roles").asArray(String.class);
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            Arrays.stream(roles).forEach(i -> list.add(new SimpleGrantedAuthority(i)));
            Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,list);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
}
