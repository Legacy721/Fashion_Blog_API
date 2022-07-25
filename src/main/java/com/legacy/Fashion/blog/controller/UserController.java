package com.legacy.Fashion.blog.controller;


import com.legacy.Fashion.blog.dto.LoginDTO;
import com.legacy.Fashion.blog.dto.SignupDTO;
import com.legacy.Fashion.blog.model.User;
import com.legacy.Fashion.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;



    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody SignupDTO signupDTO){
        return ResponseEntity.ok(userService.signup(signupDTO));
    }


    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginDTO loginDTO){
        userService.login(loginDTO);
        return new ResponseEntity<>("Login Successful", HttpStatus.OK);
    }

    @GetMapping("/log-out")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok(userService.logout());
    }

}
