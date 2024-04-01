package org.solomon.services;

import org.solomon.dtos.LoginRequest;
import org.solomon.dtos.PostRequest;
import org.solomon.dtos.RegisterRequest;
import org.solomon.exceptions.InvalidUsernameOrPassword;
import org.solomon.exceptions.UserExistsException;
import org.solomon.exceptions.UsernameNotFoundException;
import org.solomon.model.Post;
import org.solomon.model.User;
import org.solomon.repository.UserRepository;
import org.solomon.response.CreatePostResponse;
import org.solomon.response.LoginUserResponse;
import org.solomon.response.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.solomon.utilits.Mapper.*;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository users;
    @Autowired
    private PostService postService;

    @Override
    public RegisterUserResponse register(RegisterRequest register) {
        validate(register.getUsername());
        User newUser = map(register);
        User savedUser = users.save(newUser);
        return registerResponseMap(savedUser);
    }
    @Override
    public LoginUserResponse login(LoginRequest loginRequest) {
         User foundUser = findUserBy(loginRequest.getUsername());
        if (!isPasswordIncorrect(foundUser, loginRequest.getPassword())) {
            return mapLoginResponse(foundUser);
        } else {
           throw new InvalidUsernameOrPassword("Invalid Username or password");
        }

    }

    @Override
    public CreatePostResponse createPost(PostRequest postRequest) {
        User foundUser = findUserBy(postRequest.getUsername());
        Post newPost = postService.createPostWith(postRequest);
        foundUser.getPosts().add(newPost);
        users.save(foundUser);
        return mapCreatePostResponseWith(newPost);
    }

    private boolean isPasswordIncorrect(User foundUser, String password) {
       return  !foundUser.getPassword().equals(password);

    }

    private User findUserBy(String username) {
        if (users == null)
            throw new IllegalStateException("User cannot be null");

        User foundUser = users.findByUsername(username);
        if (foundUser == null)
            throw new UsernameNotFoundException(String.format("%s not found", username));

        return foundUser;
    }



private void validate(String username) {
    boolean userExists = users.existsByUsername(username);
    if (userExists) throw new UserExistsException(String.format("%s username already exists", username));

}
}


