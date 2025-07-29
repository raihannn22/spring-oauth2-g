package com.oauth_example.oauth_example.controller;

import com.oauth_example.oauth_example.model.Users;
import com.oauth_example.oauth_example.service.UsersService;
import com.oauth_example.oauth_example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsersService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        Optional<Users> validUser = userService.validate(user.getUsername(), user.getPassword());
        if (validUser.isPresent()) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;  // menampilkan info user yang login
    }
}

