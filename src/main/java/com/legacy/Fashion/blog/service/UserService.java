package com.legacy.Fashion.blog.service;


import com.legacy.Fashion.blog.dto.LoginDTO;
import com.legacy.Fashion.blog.dto.SignupDTO;
import com.legacy.Fashion.blog.model.User;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;


public interface UserService {


    User signup(SignupDTO signupDTO);

    User login (LoginDTO loginDTO);

    User findUserById(Long id);

    String logout();
}
