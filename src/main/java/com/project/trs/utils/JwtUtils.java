package com.project.trs.utils;

import com.project.trs.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "trs-app";
    public static final String TOKEN_AUDIENCE = "trs-app";
    public static final String ROLE_USER = "ROLE_USER";
    private static List<String> validatedJWTTokens = new ArrayList<>();
    public static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";
    public static final String LOGIN_SUCCESS = "Successfully logged in";
    public static final String LOGOUT_SUCCESS = "You are successfully logged out.";
    private final UserService userService;

    public static void addToken(String token) {
        validatedJWTTokens.add(token);
    }

    public static boolean isValidToken(String token) {
        return validatedJWTTokens.contains(token);
    }

    public String generateJwtToken(Authentication authentication, boolean rememberMe) {

        long validityDay = rememberMe ? 864000000 : 36000000;
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        byte[] signingKey = JWT_SECRET.getBytes();

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(userService.getUserByEmail(authentication.getName()).getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + validityDay))
                .claim("role", roles)
                .compact();

        return TOKEN_PREFIX + token;
    }

    public static void removeToken(String token) {
        if (isValidToken(token)) {
            validatedJWTTokens.remove(token);
        }
    }

    public static String getToken(HttpServletRequest request) {
        return request.getHeader(TOKEN_HEADER);
    }
}
