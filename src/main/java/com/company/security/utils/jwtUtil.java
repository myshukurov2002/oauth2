package com.company.security.utils;


import com.company.auth.AuthResp;
import com.company.user.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class jwtUtil {

    private static final String SECRET_KEY = "MAZGI_KEY!827687QT3WGDHGSAJGFDSJFGUYEWTUYSGFHGFUY9873Q893QW";
    private static long ISSUED_AT = 1000 * 3600 * 24;

    public static AuthResp encode(String email, Role role) {

        String token = Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ISSUED_AT))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return AuthResp.builder()
                .role(role)
                .token(token)
                .build();
    }

    public static AuthResp getAuth(String token) {

        Claims body = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        String email = body.getSubject();
        String role = (String) body.get("role");

        return  AuthResp.builder()
                .email(email)
                .role(Role.valueOf(role))
                .build();
    }
}
