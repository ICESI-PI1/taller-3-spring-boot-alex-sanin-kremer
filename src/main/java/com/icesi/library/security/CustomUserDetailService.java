package com.icesi.library.security;

import com.icesi.library.model.User;
import com.icesi.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> existingUserOptional = userRepository.findOneByEmail(email);

        if (!existingUserOptional.isPresent()) {
            throw new UsernameNotFoundException(String.format("The user with email %s does not exist", email));
        }

        return new UserDetailsImpl(existingUserOptional.get());
    }
}
