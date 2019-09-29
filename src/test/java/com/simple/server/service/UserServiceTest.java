package com.simple.server.service;

import com.simple.server.model.User;
import com.simple.server.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private User user;
    private User savedUser;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
        user = new User("Roman", 30);
        savedUser = new User("Roman", 30);
        savedUser.setId(1L);

        given(userRepository.save(user)).willReturn(savedUser);
        given(userRepository.save(savedUser)).willThrow(RuntimeException.class);
    }

    @Test
    public void addNewUserTest() {
        final User storedUser = userService.add(user);

        verify(userRepository).save(user);
        assertNotNull(storedUser.getId());
    }

    @Test(expected = Exception.class)
    public void whenAddUserWithIdShouldThrowException() {
        userService.add(savedUser);
    }
}
