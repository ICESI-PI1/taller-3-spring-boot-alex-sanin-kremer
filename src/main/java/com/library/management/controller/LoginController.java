package com.library.management.controller;

import com.library.management.persistence.model.LoginRequest;
import com.library.management.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final UserServiceImpl userService;

    public LoginController(final UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping( "/auth")
    public ResponseEntity findOne(@RequestBody LoginRequest loginRequest) throws Exception {
        System.out.println("Enter here");
        return ResponseEntity.ok(this.userService.getUserByNameAndPassword(loginRequest));
    }
}
