package com.portifolyo.userservice.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.service.EmailService;
import com.portifolyo.userservice.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.userservice.UserInfo;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;
    private final EmailService emailService;


    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody UserRegisterRequest userRegisterRequest) throws MessagingException {
        User u = this.userService.saveUser(userRegisterRequest);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/activeuser")
    public ResponseEntity<Void> activeUser(@RequestParam String code) {
        this.userService.activiteUser(code);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<UserInfo>> findUsers() {
        return ResponseEntity.ok(this.userService.findAllUser());
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateUsers(@JsonInclude(JsonInclude.Include.NON_NULL) @RequestBody UserRegisterRequest request) {
        this.userService.updateUser(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteUser(@RequestParam String email) {
         this.userService.deleteUser(email);
         return ResponseEntity.ok().build();
    }


}
