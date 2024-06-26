package org.kiki.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.kiki.springsecurity.dto.AuthResponseDto;
import org.kiki.springsecurity.dto.LoginRequestDto;
import org.kiki.springsecurity.dto.LoginResponse;
import org.kiki.springsecurity.dto.RegistrationDto;
import org.kiki.springsecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")  // this is what you paste in your postman
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

@PostMapping("/register-user")
    public ResponseEntity<AuthResponseDto> registerUser(
        @RequestBody RegistrationDto registrationDto){
        return ResponseEntity.ok(userService.registerUser(registrationDto));

}
@PostMapping("/Login-user")
    public ResponseEntity<LoginResponse>loginuser(@RequestBody LoginRequestDto loginRequestDto){
    return  ResponseEntity.ok(userService.loginUser(loginRequestDto));
}

}
