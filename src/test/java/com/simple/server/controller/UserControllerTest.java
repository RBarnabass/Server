package com.simple.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.simple.server.dto.UserDTO;
import com.simple.server.model.User;
import com.simple.server.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private UserDTO userDTO;

    private String json;

    @Before
    public void setUp() throws JsonProcessingException {
        userDTO = new UserDTO();
        userDTO.setName("Roman");
        userDTO.setAge(30);

        final User storedUser = new User();
        storedUser.setName(userDTO.getName());
        storedUser.setAge(userDTO.getAge());
        storedUser.setId(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        json = objectMapper.writeValueAsString(userDTO);

        given(userService.add(any(User.class))).willReturn(storedUser);
    }

    @Test
    public void addNewUserTest() throws Exception {

        mockMvc.perform(post("/api/server/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(userDTO.getName())))
                .andExpect(jsonPath("$.age", is(userDTO.getAge())));

        verify(userService).add(argThat(argument -> argument.getName().equals(userDTO.getName())));
    }
}
