package com.company.auth;


import com.company.security.utils.jwtUtil;
import com.company.user.UserEntity;
import com.company.user.enums.Role;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResp login(@RequestBody AuthDto authDto) {
        return authService.login(authDto);
    }


    @PostMapping("/register")
    public AuthResp register(@RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }


    @GetMapping("/oauth2/success")
    public AuthResp oauth2Success() {
        return authService.oauth2Success();
    }

    @GetMapping("/get-all")
    public Page<UserEntity> getAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size) {
        return authService.getAll(page, size);
    }

    @GetMapping("/get-all-by/{visibility}")
    public Page<UserEntity> getAllByVisiblity(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size,
                                              @PathVariable boolean visibility) {
        return authService.getAllByVisiblity(page, size, visibility);
    }

    @PostMapping("/default")
    public String login() {
        authService.register(
                RegisterDto.builder()
                        .email("test@gmail.com")
                        .password("test")
                        .build()
        );

        authService.register(
                RegisterDto.builder()
                        .email("alish@gmail.com")
                        .password("alish")
                        .build()
        );

        return "SUCCESS";
    }

    @PostConstruct
    public void test() {
        System.out.println("POSTConstruct");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test2() {
        System.out.println("EventListener");
    }

    @PostMapping("/view/{userId}/{articleId}")
    public ResponseEntity<?> view(@PathVariable UUID userId,
                                  @PathVariable UUID articleId) {
        return authService.view(userId, articleId);
    }
}
