package com.oauth_example.oauth_example.repo;

import com.oauth_example.oauth_example.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    boolean existsByEmail(String email);
}
