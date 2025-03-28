package com.company.user;

import com.company.component.BaseMapper;
import com.company.user.dtos.request.UserCr;
import com.company.user.dtos.response.UserResp;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<UserEntity, UserCr, UserResp> {
    
    @Override
    public UserEntity toEntity(UserCr cr) {
        return UserEntity.builder()
                .name(cr.name())
                .email(cr.email())
                .role(cr.role())
                .build();
    }

    @Override
    public UserResp toResponse(UserEntity entity) {
        return new UserResp(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getRole()
        );
    }
} 