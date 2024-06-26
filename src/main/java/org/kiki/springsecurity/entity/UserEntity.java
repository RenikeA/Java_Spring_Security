package org.kiki.springsecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import org.kiki.springsecurity.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity // @Entity: This annotation specifies that the class is an entity
// and is mapped to a database table.

@Table(name = "user_tbl") //@Table(name = "user_tbl"): This specifies the table name in the database.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ; //Unique identifier for the user (Primary Key).


    private String firstName;

    private String lastName;

    private String email; //Unique identifier for the user (Primary Key).


    private String password; //User's role in the application

    private Role role;

//    getAuthorities(): This returns the roles of the user. In this case,
//     it returns a list containing a single authority (role) for the user.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    @Override
    public String getPassword() {
        return password;
    }
//Returns the user's password.
    @Override
    public String getUsername() {
        return email;
    }
//    Returns the user's email, which serves as the username.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
//     Indicates whether the user's account has expired.
//     Returning true means the account is valid

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
//     Indicates whether the user is locked or unlocked.
//     Returning true means the user is not locked.

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
//     Indicates whether the user's credentials (password) have expired.
//     Returning true means the credentials are valid.

    @Override
    public boolean isEnabled() {
        return true;
    }
////    Indicates whether the user is enabled or disabled.
////    Returning true means the user is enabled.
////    The UserEntity class is crucial in managing user data in your application's database and integrating with Spring Security for authentication and authorization.
////    It stores user details, provides necessary information for security processes,
////    and works seamlessly with other data transfer objects like RegistrationDto
////    and AuthResponseDto to ensure a smooth registration and authentication process.
}
