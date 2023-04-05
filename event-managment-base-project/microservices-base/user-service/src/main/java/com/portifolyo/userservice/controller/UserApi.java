package com.portifolyo.userservice.controller;

import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.service.UserService;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserApi {
    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        User u = this.userService.saveUser(userRegisterRequest);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/activeuser")
    public ResponseEntity<Void> activeUser(@RequestParam String code) {
        this.userService.activiteUser(code);
        return ResponseEntity.ok().build();
    }

}
