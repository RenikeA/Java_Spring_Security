package org.kiki.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//THIS CLASS WILL HELP US RETURN user Responses, this is the response we want to see as a developer ,
// your response should not carry password.
// This class is similar to RegistrationDto, but it's used for a different purpose.
// Instead of containing the data the user sends when signing up,
// it contains the data that we send back to the user after they have registered.
// Importantly, it does not include the password,
// because for security reasons, we don't want to send passwords back in responses.
public class RegistrationInfo {

    private  String firstName;

    private  String lastName;

    private  String email;

}







/*
How They Work Together
User Registration:
When a user wants to sign up, they send their details (first name, last name, email, password) to the server using the RegistrationDto class.
Server Processing:
The server processes the registration request. If everything goes well, it creates a new account for the user.
Response to User:
After processing the request, the server sends a response back to the user using the AuthResponseDto class. This response includes:
A response code indicating success or failure.
A message explaining the result (e.g., "Account created successfully").
The user's details (but not the password) using the RegistrationInfo class.

*
*
* */