package com.portifolyo.organizercompanyservice.feign;

import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.portifolyo.response.GenericResponse;
import org.portifolyo.response.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE")
public interface UserFeign {

    UserInfo getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                         @RequestParam String email);
    @PostMapping("/user/")
    ResponseEntity<GenericResponse<UserInfo>> registerUser(UserRegisterRequest userRegisterRequest);

    @DeleteMapping("/user/")
    ResponseEntity<GenericResponse<Void>> deleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,@RequestParam String email);
}
