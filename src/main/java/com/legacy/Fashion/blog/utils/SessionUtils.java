package com.legacy.Fashion.blog.utils;

import com.legacy.Fashion.blog.exceptions.UserNotFoundException;
import com.legacy.Fashion.blog.model.User;
import com.legacy.Fashion.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;


@Component
@RequiredArgsConstructor
public class SessionUtils {

    private final HttpSession httpSession;

    private final UserRepository userRepository;

    public Long getLoggedInUser(){
        Long userId = (Long) httpSession.getAttribute("user_id");
        if(userId == null) {
            throw new UserNotFoundException("You are not yet logged in!");
        }
        return userId;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User Not Found!"));
    }
}
