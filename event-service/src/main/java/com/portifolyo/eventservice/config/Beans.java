package com.portifolyo.eventservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;

public class Beans {


    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
