package com.company.user;

import com.company.security.utils.contextHolder;
import com.company.user.dtos.request.UserCr;
import com.company.user.dtos.response.UserResp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserResp create(UserCr cr) {
        UserEntity entity = userMapper.toEntity(cr);
        entity.setPassword(passwordEncoder.encode(cr.password()));

        System.out.println(contextHolder.getUser());

        SecurityContextHolder.getContext();
        return userMapper.toResponse(userRepository.save(entity));
    }


    public UserResp update(UUID id, UserCr cr) {
        UserEntity entity = userRepository.findByIdAndVisibilityTrue(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        entity.setName(cr.name());
        entity.setEmail(cr.email());
        if (cr.password() != null) {
            entity.setPassword(passwordEncoder.encode(cr.password()));
        }
        entity.setRole(cr.role());
        
        return userMapper.toResponse(userRepository.save(entity));
    }


    public UserResp getById(UUID id) {
        return userMapper.toResponse(userRepository.findByIdAndVisibilityTrue(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }


    public Page<UserResp> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<UserEntity> all = userRepository.findAll(pageable);

        List<UserResp> list = all.stream()
                .map(userMapper::toResponse)
                .toList();

        return new PageImpl<>(list ,pageable, list.size());
    }


    public void delete(UUID id) {
        UserEntity entity = userRepository.findByIdAndVisibilityTrue(id)
                .orElseThrow(() -> new RuntimeException("User not found!!!"));

        entity.setVisibility(false);
        userMapper.toResponse(userRepository.save(entity));
    }
} 