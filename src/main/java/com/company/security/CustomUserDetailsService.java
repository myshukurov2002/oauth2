package com.company.security;

import com.company.user.UserEntity;
import com.company.user.UserRepository;
import com.company.user.enums.Role;
import com.company.user.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmailAndVisibilityTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found!!!"));
    }

    public UserEntity findByEmail(String email, String name) {
        Optional<UserEntity> optionalUser = userRepository
                .findByEmailAndVisibilityTrue(email);

        if (optionalUser.isEmpty()) {
            UserEntity newUser = new UserEntity(
                    name,
                    email,
                    "12345678",
                    Role.USER,
                    Status.ACTIVE
            );
            userRepository.save(newUser);
        }
        return optionalUser.get();
    }
}
