package com.project.service.taxi.controller;

import com.project.service.taxi.auth.AuthRequest;
import com.project.service.taxi.auth.AuthResponse;
import com.project.service.taxi.dto.UserRequestDTO;
import com.project.service.taxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthAndRegisterController {
    private final UserService userService;
    @Autowired
    public AuthAndRegisterController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/register")
    public ResponseEntity<AuthResponse> saveUser(@RequestBody UserRequestDTO userRequestDTO){
        System.out.println(userRequestDTO);
       return new ResponseEntity<>(userService.saveUser(userRequestDTO),HttpStatus.CREATED) ;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> checkLoginData(@Validated @RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = userService.checkLoginAndPassword(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
