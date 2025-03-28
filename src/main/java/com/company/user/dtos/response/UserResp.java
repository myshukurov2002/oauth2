package com.company.user.dtos.response;

import com.company.user.enums.Role;

import java.util.UUID;

public record UserResp(
        UUID id,
        String name,
        String email,
        Role role
) {
}
