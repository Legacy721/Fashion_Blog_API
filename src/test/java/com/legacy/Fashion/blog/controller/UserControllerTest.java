package com.legacy.Fashion.blog.controller;

import com.legacy.Fashion.blog.dto.SignupDTO;
import com.legacy.Fashion.blog.enums.Role;
import com.legacy.Fashion.blog.model.User;
import com.legacy.Fashion.blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(UserController.class)
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username("legacy")
                .email("legacy@gmail.com")
                .password("123456")
                .role(Role.FOLLOWER)
                .userId(1L)
                .build();

    }


    @Test
    @Disabled
    void saveUser() throws Exception{

        SignupDTO signupDTO = new SignupDTO();
        User registerUser = User.builder()
                .username("legacy")
                .email("legacy@gmail.com")
                .password("123456")
                .build();

        BeanUtils.copyProperties(registerUser, signupDTO);
        Mockito.when(userService.signup(signupDTO)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"username\":\"legacy\",\n" +
                        "    \"email\":\"legacy@gmail.com\",\n" +
                        "    \"password\":\"123456\"\n" +
                        "}")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}