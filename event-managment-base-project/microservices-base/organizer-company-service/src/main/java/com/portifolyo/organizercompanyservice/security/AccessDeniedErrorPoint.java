package com.portifolyo.organizercompanyservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.portifolyo.response.GenericResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
@Component
public class AccessDeniedErrorPoint implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    public AccessDeniedErrorPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(405);
        response.setContentType("application/json");
        GenericResponse<Void> res = new GenericResponse<>(405,"Access Denied");
        PrintWriter writer = response.getWriter();
        writer.print(objectMapper.writeValueAsString(res));
        writer.flush();
    }
}
