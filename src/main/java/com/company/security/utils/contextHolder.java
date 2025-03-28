package com.company.security.utils;

import com.company.user.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class contextHolder {

    public static UserEntity getUser() {
        return (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
