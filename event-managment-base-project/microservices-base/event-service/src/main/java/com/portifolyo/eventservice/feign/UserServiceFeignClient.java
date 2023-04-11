package com.portifolyo.eventservice.feign;

import org.portifolyo.requests.userservice.UserInfo;
import org.portifolyo.response.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="USER-SERVICE")
public interface UserServiceFeignClient {
    @GetMapping("user/finduser")
    ResponseEntity<GenericResponse<UserInfo>> findUserByEmail(@RequestParam String email);

}
