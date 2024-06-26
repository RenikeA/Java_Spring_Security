package org.kiki.springsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import org.kiki.springsecurity.config.JwtService;
import org.kiki.springsecurity.dto.*;
import org.kiki.springsecurity.entity.UserEntity;
import org.kiki.springsecurity.enums.Role;
import org.kiki.springsecurity.repository.UserRepository;
import org.kiki.springsecurity.service.EmailService;
import org.kiki.springsecurity.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    @Override
    public AuthResponseDto registerUser(RegistrationDto registrationDto) {
// this is what you will get/created postman
        UserEntity user = UserEntity.builder()
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

//        send email alert
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("Account Creation")
                .messagebody("Congratulations! Your Account Has Been Succesfully Created.")
                .build();

        emailService.sendEmaiAlert(emailDetails);

        return AuthResponseDto.builder()
                .responseCode("001")
                .responseMessage("Account Created Succesfully")
                .registrationInfo(RegistrationInfo.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getFirstName())
                        .email(user.getEmail())
                        .build())
                .build();
    }

    @Override
    public LoginResponse loginUser(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        UserEntity user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return  LoginResponse.builder()
                .responsecode("002")
                .responseMessage("Login Successfully")
                .loginInfo(LoginInfo.builder()
                        .email(user.getEmail())
                        .token(jwtToken)
                        .build())
                .build();


    }
}
