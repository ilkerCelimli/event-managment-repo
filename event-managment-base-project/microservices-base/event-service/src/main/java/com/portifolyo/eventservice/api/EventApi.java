package com.portifolyo.eventservice.api;

import com.portifolyo.eventservice.feign.UserServiceFeignClient;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.userservice.UserInfo;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventApi {


}
