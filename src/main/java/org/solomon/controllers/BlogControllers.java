package org.solomon.controllers;

import org.solomon.dtos.LoginRequest;
import org.solomon.dtos.PostRequest;
import org.solomon.dtos.RegisterRequest;
import org.solomon.response.ApiResponse;
import org.solomon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController

public class BlogControllers {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            var result = userService.register(request);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }


    }

    @PatchMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            var result = userService.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }

    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequest createPostRequest) {
        try {
            var result = userService.createPost(createPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}