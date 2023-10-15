package com.icesi.library.service;

import com.icesi.library.model.User;
import com.icesi.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(final User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userRepository.save(user);
    }

    public void login(final User user) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userEmail = user.getEmail();
        Optional<User> existingUserOptional = userRepository.findOneByEmail(userEmail);
        if (!existingUserOptional.isPresent()) {
            throw new Exception(String.format("The user with email %s does not exist.", userEmail));
        }

        User existingUser = existingUserOptional.get();
        if (!bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new Exception(String.format("The password does not match"));
        }
    }

}
