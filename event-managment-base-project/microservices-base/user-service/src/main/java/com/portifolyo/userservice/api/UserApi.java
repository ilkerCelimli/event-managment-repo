package com.portifolyo.userservice.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.service.EmailService;
import com.portifolyo.userservice.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.userservice.UserInfo;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;
    private final EmailService emailService;


    @PostMapping("/")
    public ResponseEntity<GenericResponse<Void>> saveUser(@RequestBody UserRegisterRequest userRegisterRequest) throws MessagingException {
        User u = this.userService.saveUser(userRegisterRequest);
        return ResponseEntity.ok(new GenericResponse<>(200,"created User"));
    }

    @GetMapping("/activeuser")
    public ResponseEntity<GenericResponse<Void>> activeUser(@RequestParam String code) {
        this.userService.activiteUser(code);
        return ResponseEntity.ok(new GenericResponse<>(200,"User Activited"));
    }

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<UserInfo>>> findUsers() {
        return ResponseEntity.ok(new GenericResponse<>(200,null,this.userService.findAllUser()));
    }

    @PutMapping("/")
    public ResponseEntity<GenericResponse<Void>> updateUsers(@JsonInclude(JsonInclude.Include.NON_NULL) @RequestBody UserRegisterRequest request) {
        this.userService.updateUser(request);
        return ResponseEntity.ok(new GenericResponse<>(200,"User Updated"));
    }

    @DeleteMapping("/")
    public ResponseEntity<GenericResponse<Void>> deleteUser(@RequestParam String email) {
         this.userService.deleteUser(email);
         return ResponseEntity.ok(new GenericResponse<>(200,"user inactived"));
    }
    @GetMapping(value = "/finduser")
    public ResponseEntity<GenericResponse<UserInfo>>findUserByEmail(@RequestParam String email){
        UserInfo u = this.userService.findUserByEmail(email);
       return u != null ? ResponseEntity.ok(new GenericResponse<>(200,"Email is Found",u)) :
               ResponseEntity.ok().build();
    }


}
