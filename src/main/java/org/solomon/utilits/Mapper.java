package org.solomon.utilits;

import org.solomon.dtos.PostRequest;
import org.solomon.dtos.RegisterRequest;
import org.solomon.model.Post;
import org.solomon.model.User;
import org.solomon.response.CreatePostResponse;
import org.solomon.response.LoginUserResponse;
import org.solomon.response.RegisterUserResponse;

import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setLastName(registerRequest.getLastName());
        user.setFirstName(registerRequest.getFirstName());
        return user;
    }

    public static RegisterUserResponse registerResponseMap(User user) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setId(user.getId());
        registerUserResponse.setUsername(user.getUsername());
        registerUserResponse.setDateTimeRegistered(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(user.getDateRegistered()));
        return registerUserResponse;

    }

    public static LoginUserResponse mapLoginResponse(User user) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        loginUserResponse.setId(user.getId());
        loginUserResponse.setUsername(user.getUsername());
        return loginUserResponse;

    }

    public static Post map(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return post;
    }
    public static CreatePostResponse mapCreatePostResponseWith(Post post) {
        CreatePostResponse createPostResponse = new CreatePostResponse();
        createPostResponse.setId(post.getId());
        createPostResponse.setTitle(post.getTitle());
        createPostResponse.setContent(post.getContent());
        createPostResponse.setDateCreated(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(post.getDateCreated()));
        return createPostResponse;
    }
}

