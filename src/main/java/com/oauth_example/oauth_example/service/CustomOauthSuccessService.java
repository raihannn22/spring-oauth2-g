package com.oauth_example.oauth_example.service;

import com.oauth_example.oauth_example.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Component
public class CustomOauthSuccessService implements AuthenticationSuccessHandler {

    @Autowired
    JwtUtil jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");

        String token = jwtService.generateToken(email);

        Cookie cookie = new Cookie("auth-token", token);
        cookie.setHttpOnly(true);           // Tidak bisa diakses oleh JavaScript
        cookie.setSecure(true);             // Hanya HTTPS (di production)
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 hari
        response.addCookie(cookie);
        // Redirect ke FE dengan JWT (atau bisa juga taruh di cookie / body JSON)
        response.sendRedirect("http://localhost:8080/swagger-ui");

    }
}
