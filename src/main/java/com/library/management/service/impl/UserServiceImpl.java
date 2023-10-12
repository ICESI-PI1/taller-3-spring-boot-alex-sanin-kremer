package com.library.management.service.impl;

import com.library.management.persistence.model.LoginRequest;
import com.library.management.persistence.model.LoginResponse;
import com.library.management.persistence.model.User;
import com.library.management.persistence.repository.impl.UserRepositoryImpl;
import com.library.management.security.JwtUtil;
import com.library.management.security.JwtUtil2;
import com.library.management.service.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepositoryImpl userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil2 jwtUtil;

    public UserServiceImpl(final UserRepositoryImpl userRepository,
                           final AuthenticationManager authenticationManager,
                           final JwtUtil2 jwtUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponse getUserByNameAndPassword(final LoginRequest loginRequest) throws Exception {
        String name = loginRequest.getName();
        String password = loginRequest.getPassword();
        Optional<User> userOptional = userRepository.findByNameAndPassword(name, password);
        if (!userOptional.isPresent()) {
            throw new Exception("Login failed, the data is wrong!!!");
        }
        String role = userOptional.get().getName().equals("user1") ? "USER" : "ADMIN";
        String token = jwtUtil.generateToken(name, role);
        return new LoginResponse(name, token);
    }
}
