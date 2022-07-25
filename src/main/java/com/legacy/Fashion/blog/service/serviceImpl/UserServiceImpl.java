package com.legacy.Fashion.blog.service.serviceImpl;


import com.legacy.Fashion.blog.dto.LoginDTO;
import com.legacy.Fashion.blog.dto.SignupDTO;
import com.legacy.Fashion.blog.enums.Role;
import com.legacy.Fashion.blog.exceptions.InvalidLoginDetailsException;
import com.legacy.Fashion.blog.exceptions.UserAlreadyExistException;
import com.legacy.Fashion.blog.exceptions.UserNotFoundException;
import com.legacy.Fashion.blog.model.User;
import com.legacy.Fashion.blog.repository.UserRepository;
import com.legacy.Fashion.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final HttpSession httpSession;




    @Override
    public User signup(SignupDTO signupDTO) {

        String email = signupDTO.getEmail();
        if(userRepository.existsByEmail(email)){
            throw new UserAlreadyExistException("Users with " + email+ "already exists");
        }
//        User user = new User();
//        BeanUtils.copyProperties(signupDTO, user);
        User user = User.builder()
                .email(signupDTO.getEmail())
                .password(signupDTO.getPassword())
                .role(Role.FOLLOWER)
                .username(signupDTO.getUsername())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User login(LoginDTO loginDTO) {

        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if(!(user.getPassword().equals(loginDTO.getPassword()))){
            throw new InvalidLoginDetailsException("Invalid Login Details provided");
        }
        httpSession.setAttribute("user_id", user.getUserId());
        httpSession.setAttribute("Permission", "User");
        return user;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    @Override
    public String logout() {
        httpSession.invalidate();
        return "User has Successfully been Logged Out";
    }
}
