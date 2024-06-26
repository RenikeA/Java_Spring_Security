package org.kiki.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDto {
//    This class is used to send a response back to the user after they perform some action,
//    such as creating an account. It includes three main pieces of information:

    private String responseCode;// GENERATE CODE FROM THE PROGRAMMER SIDE, responseCode:
    // A code that indicates the result of the action. For example, it might be "200" for success
    // or "400" for an error.

    private String responseMessage;// RETURN RESPONSE MESSAGE " ACCOUNT CREATED SUCCESFULLY"

    private RegistrationInfo registrationInfo;// COMPOSITION , GIVE US ACCESS TO USER DETAILS ,
    // WHILE BUILDING ON AUTHRESPONSE,This is another object that contains the details of the user who performed the
    // action (like their name and email).
}
