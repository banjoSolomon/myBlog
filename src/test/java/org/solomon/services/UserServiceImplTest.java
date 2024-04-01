package org.solomon.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.solomon.dtos.LoginRequest;
import org.solomon.dtos.PostRequest;
import org.solomon.dtos.RegisterRequest;
import org.solomon.exceptions.IncorrectPasswordException;
import org.solomon.exceptions.InvalidUsernameOrPassword;
import org.solomon.exceptions.UserExistsException;
import org.solomon.exceptions.UsernameNotFoundException;
import org.solomon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

@SpringBootTest
public class UserServiceImplTest {

    @BeforeEach
    public void setup() {
        user.deleteAll();

        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Banjo");
        registerRequest.setLastName("Solomon");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");

        loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");

        postRequest = new PostRequest();
        postRequest.setUsername("username");
        postRequest.setTitle("title");
        postRequest.setContent("content");

    }

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository user;
    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;
    private PostRequest postRequest;



    @Test
    public void testUserCanRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Banjo");
        registerRequest.setLastName("Solomon");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userService.register(registerRequest);
        assertThat(user.count(), is(1L));


    }

    @Test
    public void testRegisterSameUserTwice() {
        RegisterRequest registerRequest = new RegisterRequest();
        userService.register(registerRequest);
        try {
            userService.register(registerRequest);
        } catch (UserExistsException e) {
            assertThat(e.getMessage(), containsString("username already exists"));
        }
         assertThat(user.count(), is(1L));


    }

    @Test
    public void testUserCanLogin_WithCorrectPassword() {
        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Banjo");
        registerRequest.setLastName("Solomon");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");

        userService.register(registerRequest);
        assertThat(user.count(), is(1L));
        loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        var loginResponse = userService.login(loginRequest);
        assertThat(loginResponse.getId(), notNullValue());

    }

    @Test
    public void loginNonExistentUser_throwsExceptionTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("existing_username");
        registerRequest.setPassword("password");
        userService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Non existent username");
        try {
            userService.login(loginRequest);
        } catch (UsernameNotFoundException e) {
            assertThat(e.getMessage(), containsString("Non existent username not found"));
        }

    }

    @Test
    public void loginWithIncorrectPassword_throwsExceptionTest() {
        userService.register(registerRequest);
        loginRequest.setPassword("incorrectPassword");
        try {
            userService.login(loginRequest);
        } catch (InvalidUsernameOrPassword e) {
            assertThat(e.getMessage(), containsString("Invalid Username or password"));
        }
    }
    @Test
    public void userCanCreatePost_PostIsOneIsOneTest(){
        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Banjo");
        registerRequest.setLastName("Solomon");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");

        userService.register(registerRequest);
        var checkUser = user.findByUsername(registerRequest.getUsername());
        assertThat(checkUser.getPosts().size(), is(0));
        postRequest = new PostRequest();
        postRequest.setUsername(registerRequest.getUsername());
        postRequest.setTitle("Title");
        postRequest.setContent("Content");
        var createPostResponse = userService.createPost(postRequest);
        checkUser = user.findByUsername(registerRequest.getUsername());
        assertThat(checkUser.getPosts().size(), is(1));
        assertThat(createPostResponse.getId(), notNullValue());
    }

}