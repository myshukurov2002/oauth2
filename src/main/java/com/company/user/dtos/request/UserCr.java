package com.company.user.dtos.request;

import com.company.user.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCr(
        @NotBlank(message = "Name Must Not Blank!!!")
        String name,

        @NotBlank(message = "Email Must Not Blank!!!")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,6}$", message = "Invalid Email!!!")
        String email,

        @NotBlank(message = "Password Must Not Blank!!!")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
                message = "Parol kamida bitta katta harf, bitta kichik harf, bitta raqam va bitta maxsus belgidan iborat bo‘lishi kerak"
        )
        String password,

        @NotBlank(message = "Role Must Not Blank!!!")
        Role role
) {
}
