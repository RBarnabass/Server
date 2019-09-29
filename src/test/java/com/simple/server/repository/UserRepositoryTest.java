package com.simple.server.repository;

import com.simple.server.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired private UserRepository userRepository;

    private User user;

    @Before
    public void setUp() {
        user = new User("Roman", 30);
    }

    @Test
    public void addUserTest() {
        final User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
    }

    @Test(expected = Exception.class)
    public void whenAddUserWithExistenceNameShouldThrowException() {
        userRepository.save(user);
        userRepository.save(user);
    }

    @Test(expected = Exception.class)
    public void whenAddUserWithoutNameShouldThrowException() {
        user.setName(null);
        userRepository.save(user);
    }

    @Test(expected = Exception.class)
    public void whenAddUserWithoutAgeShouldThrowException() {
        user.setAge(null);
        userRepository.save(user);
    }
}
