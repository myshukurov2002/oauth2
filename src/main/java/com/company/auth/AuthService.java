package com.company.auth;

import com.company.article.views.ViewEntity;
import com.company.article.views.ViewRepository;
import com.company.expections.exp.DuplicateItemException;
import com.company.security.utils.contextHolder;
import com.company.security.utils.jwtUtil;
import com.company.user.enums.Role;
import com.company.user.enums.Status;
import com.company.user.UserEntity;
import com.company.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthMapper authMapper;
    private final ViewRepository viewRepository;

    public AuthResp login(AuthDto authDto) {
//        return userRepository
//                .findByEmailAndPassword(authDto.getEmail(), bCryptPasswordEncoder.encode(authDto.getPassword()))
//                .orElseThrow();

        String email = authDto.getEmail();

        UserEntity user = userRepository
                .findByEmailAndVisibilityTrue(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found!!!"));

        boolean matches = bCryptPasswordEncoder
                .matches(authDto.getPassword(), user.getPassword());

        if (!matches) {
            throw new RuntimeException("Invalid Password!!!");
        }


        return authMapper.apply( userRepository.save(user)) ;
    }

    public AuthResp register(RegisterDto registerDto) {

        Optional<UserEntity> optionalUser = userRepository
                .findByEmailAndVisibilityTrue(registerDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new DuplicateItemException();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(registerDto.getFullName());
        userEntity.setEmail(registerDto.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        userEntity.setRole(registerDto.getRole());
        userEntity.setStatus(Status.REGISTER);

        return authMapper.apply( userRepository.save(userEntity)) ;
    }

    public Page<UserEntity> getAll(int page, int size) {
//        return userRepository.findAll(PageRequest.of(page, size));
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public Page<UserEntity> getAllByVisiblity(int page, int size, boolean visibility) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Sort sort = Sort.by("createdAt")
                .descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return
                userRepository
                        .findAllByVisibility(visibility, pageable);
    }

    public ResponseEntity<?> view(UUID userId, UUID articleId) {

        viewRepository.findByUserIdAndArticleId(userId, articleId)
                .ifPresent(v -> {
                    throw  new RuntimeException();
                });

        ViewEntity entiy = new ViewEntity();
        entiy.setArticleId(articleId);
        entiy.setUserId(userId);

        viewRepository.save(entiy);
        return ResponseEntity.ok("SUCCESS");
    }

    public AuthResp oauth2Success() {

        UserEntity contextUser = contextHolder.getUser();

        if (contextUser == null) {
            throw new RuntimeException("Authentication not found!");
        }

        String email = contextUser.getEmail();
        String name = contextUser.getName();

        // Foydalanuvchini bazadan qidirish
        Optional<UserEntity> optionalUser = userRepository
                .findByEmailAndVisibilityTrue(email);

        UserEntity user;

        if (optionalUser.isEmpty()) {
            // Agar foydalanuvchi bazada bo'lmasa - registratsiya qilamiz
            user = new UserEntity();
            user.setEmail(email);
            user.setName(name);
            user.setRole(Role.USER);
            user.setStatus(Status.ACTIVE);
            userRepository.save(user);  // Bazaga saqlash
            System.out.println("Yangi foydalanuvchi ro'yxatdan o'tdi: " + email);

        } else {
            // Foydalanuvchi mavjud - login
            user = optionalUser.get();
            System.out.println("Foydalanuvchi mavjud, login qilindi: " + email);
        }

        // JWT token yaratish
        AuthResp authResp = jwtUtil.encode(user.getEmail(), user.getRole());
        return authResp;
    }
}
