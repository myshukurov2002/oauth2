package com.company.auth;

import com.company.security.utils.jwtUtil;
import com.company.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AuthMapper implements Function<UserEntity, AuthResp> {
    @Override
    public AuthResp apply(UserEntity userEntity) {
        return AuthResp.builder()
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .token(
                        jwtUtil.encode(userEntity.getEmail(), userEntity.getRole())
                                .getToken()
                ).build();
    }
}
