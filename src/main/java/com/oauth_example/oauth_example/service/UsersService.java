package com.oauth_example.oauth_example.service;

import com.oauth_example.oauth_example.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    Optional<Users> validate(String username, String password);

    List<Users> findAll();
}
