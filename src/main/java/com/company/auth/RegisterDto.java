package com.company.auth;


import com.company.user.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDto {

    private String fullName;
    private String email;
    private Role role;
    private String password;
}
