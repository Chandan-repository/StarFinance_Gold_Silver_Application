package com.java.stargold.authenticationservice.service;

import com.java.stargold.authenticationservice.entity.User;
import com.java.stargold.authenticationservice.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private  final UserRepository repo;

    private final PasswordEncoder encoder;

    public AuthService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }



    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username).orElseThrow();
    }

}
