package com.oauth_example.oauth_example.service;

import com.oauth_example.oauth_example.model.Users;
import com.oauth_example.oauth_example.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    UsersRepo usersRepo;

    @Override
    public Optional<Users> validate(String username, String password) {
        return usersRepo.findByUsername(username)
                .filter(user -> user.getPassword().equals(password));

    }

    @Override
    public List<Users> findAll() {
        return usersRepo.findAll();
    }
}
