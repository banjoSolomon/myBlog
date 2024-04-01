package org.solomon.services;

import org.solomon.dtos.LoginRequest;
import org.solomon.dtos.PostRequest;
import org.solomon.dtos.RegisterRequest;
import org.solomon.response.CreatePostResponse;
import org.solomon.response.LoginUserResponse;
import org.solomon.response.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse register(RegisterRequest registerRequest);

    LoginUserResponse login(LoginRequest loginRequest);

    CreatePostResponse createPost(PostRequest postRequest);
}

