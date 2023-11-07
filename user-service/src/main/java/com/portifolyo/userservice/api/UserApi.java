package com.portifolyo.userservice.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portifolyo.userservice.entity.Roles;
import com.portifolyo.userservice.entity.User;
import com.portifolyo.userservice.enums.Role;
import com.portifolyo.userservice.repository.RoleRepository;
import com.portifolyo.userservice.services.UserService;
import com.portifolyo.userservice.util.converter.UserInfoMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.userservice.UserLoginRequest;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.portifolyo.response.GenericResponse;
import org.portifolyo.response.TokenResponse;
import org.portifolyo.response.UserInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;
    private final RoleRepository roleRepository;
    @PostMapping("/addrole")
    public ResponseEntity<Void> addRole(){
        this.roleRepository.save(new Roles(null, Role.ROLE_COMPANY_USER.getValue()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<UserInfo>> saveUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        User u = this.userService.saveUser(userRegisterRequest);

        return ResponseEntity.ok(GenericResponse.SUCCESS(UserInfoMapper.toDto(u)));
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<TokenResponse>> login(@RequestBody @Valid UserLoginRequest userLoginRequest,
                                                                HttpServletResponse response,
                                                                HttpServletRequest httpServletRequest){
       TokenResponse tokenResponse =  this.userService.tokenResponse(userLoginRequest,httpServletRequest.getRemoteHost());
       response.addHeader(HttpHeaders.AUTHORIZATION, tokenResponse.getAccessToken());
       response.addHeader("REFRESH_TOKEN",tokenResponse.getRefreshToken());
       return ResponseEntity.ok(GenericResponse.SUCCESS(tokenResponse));
    }

    @PostMapping("/refresh")
    public ResponseEntity<GenericResponse<TokenResponse>> refresh(@RequestBody TokenResponse token, HttpServletRequest request) {
        String ip = request.getRemoteHost();
        TokenResponse tokenResponse = this.userService.tokenResponse(token.getRefreshToken(),ip);
        return ResponseEntity.ok(GenericResponse.SUCCESS(tokenResponse));
    }
    @GetMapping("/activeuser")
    public ResponseEntity<GenericResponse<Void>> activeUser(@RequestParam String code) {
        this.userService.activiteUser(code);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<UserInfo>>> findUsers() {
        return ResponseEntity.ok(GenericResponse.SUCCESS(this.userService.findAllUser()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> updateUsers(@JsonInclude(JsonInclude.Include.NON_NULL) @RequestBody UserRegisterRequest request,@PathVariable String id) {
        this.userService.updateUser(request,id);
        return ResponseEntity.ok(new GenericResponse<>(200,"User Updated"));
    }

    @DeleteMapping("/")
    public ResponseEntity<GenericResponse<Void>> deleteUser(@RequestParam String email) {
         this.userService.deleteUser(email);
         return ResponseEntity.ok(new GenericResponse<>(200,"user inactived"));
    }
    @GetMapping("/finduser")
    public ResponseEntity<GenericResponse<UserInfo>>findUserByEmail(@RequestParam String email){
        UserInfo u = this.userService.findUserByEmail(email);
       return u != null ? ResponseEntity.ok(new GenericResponse<>(200,null,u)) :
               ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<GenericResponse<Void>> deleteById(@PathVariable String id){
        this.userService.deleteUserById(id);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @GetMapping("/findById")

    public ResponseEntity<GenericResponse<UserInfo>> findById(@RequestParam String id){
        return ResponseEntity.ok(
                GenericResponse.SUCCESS(
                        this.userService.findById(id)
                )
        );
    }

}
