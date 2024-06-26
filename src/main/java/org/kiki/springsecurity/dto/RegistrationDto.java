package org.kiki.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//THIS CLASS WILL HELP US RETURN user REQUEST,
// This class represents the data that a user sends when they want
// to register (sign up) for something, like creating an account
public class RegistrationDto {

    private  String firstName;

    private  String lastName;

    private  String email;

    private String password;
}
