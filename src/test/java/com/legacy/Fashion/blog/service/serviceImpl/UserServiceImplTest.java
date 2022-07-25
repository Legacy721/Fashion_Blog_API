package com.legacy.Fashion.blog.service.serviceImpl;

import com.legacy.Fashion.blog.dto.LoginDTO;
import com.legacy.Fashion.blog.enums.Role;
import com.legacy.Fashion.blog.model.User;
import com.legacy.Fashion.blog.repository.UserRepository;
import com.legacy.Fashion.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void saveUserAdmin(){
        User user = User.builder()
                .username("admin")
                .role(Role.ADMIN)
                .email("admin@gmail.com")
                .password("12345678")
                .build();

        userRepository.save(user);
    }

    @Test
    public void saveUser(){
        User user = User.builder()
                .username("legacy")
                .email("enoch@gmail.com")
                .password("1234567")
                .role(Role.FOLLOWER)
                .build();
        userRepository.save(user);
    }


    @Test
    void login(){
        LoginDTO loginDto = LoginDTO.builder()
                .email("enoch@gmail.com")
                .password("1234567")
                .build();
        assertEquals(loginDto.getEmail(), userService.login(loginDto).getEmail());
    }

}