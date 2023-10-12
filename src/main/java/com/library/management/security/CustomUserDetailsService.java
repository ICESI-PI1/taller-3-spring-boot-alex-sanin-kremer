package com.library.management.security;

import com.library.management.persistence.model.User;
import com.library.management.persistence.repository.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Enter LoadUserByName");
        System.out.println("Name "+username);
        Optional<User> userOptional = this.userRepository.findByName(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(String.format("The user %s does not exist.", username));
        }
        User user = userOptional.get();
        String rol = user.getName().equals("user1") ? "USER" : "ADMIN";
        System.out.println("rol "+rol);
        System.out.println("name "+user.getName());
        System.out.println("password "+user.getPassword());
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(rol)
                .build();
        return userDetails;
    }
}
