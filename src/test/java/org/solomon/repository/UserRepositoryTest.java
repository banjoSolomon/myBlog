package org.solomon.repository;

import lombok.RequiredArgsConstructor;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.solomon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest

public class UserRepositoryTest {


    @Autowired
        private  UserRepository userRepository;

        @Test
        public void saveTest(){
           userRepository.deleteAll();
            User user1 =new User();
            userRepository.save(user1);
            assertThat(userRepository.count(), is(1L));
        }




    }

