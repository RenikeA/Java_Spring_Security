package org.kiki.springsecurity.service;

import org.kiki.springsecurity.dto.AuthResponseDto;
import org.kiki.springsecurity.dto.LoginRequestDto;
import org.kiki.springsecurity.dto.LoginResponse;
import org.kiki.springsecurity.dto.RegistrationDto;
//This class represents a user in your application's database.
// It includes various attributes and methods to manage user details and roles.
public interface UserService {
    AuthResponseDto registerUser (RegistrationDto registrationDto);
//    this is our interface class that only takes abstarct method , this method have a return type
//    will help us get response , when the user sign in.

    LoginResponse loginUser(LoginRequestDto loginRequestDto);
}
