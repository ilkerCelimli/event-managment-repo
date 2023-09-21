package com.portifolyo.organizercompanyservice.feign;

import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.portifolyo.response.GenericResponse;
import org.portifolyo.response.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("USER-SERVICE")
public interface UserFeign {
    @GetMapping("/user/finduser")
    UserInfo findUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                         @RequestParam String email);
    @PostMapping("/user/register")
    ResponseEntity<GenericResponse<UserInfo>> registerUser(@RequestBody UserRegisterRequest userRegisterRequest);

    @DeleteMapping("/user/")
    ResponseEntity<GenericResponse<Void>> deleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,@RequestParam String email);

    @DeleteMapping("/user/delete-by-id/{id}")
    ResponseEntity<GenericResponse<Void>> deleteById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String id);

}
