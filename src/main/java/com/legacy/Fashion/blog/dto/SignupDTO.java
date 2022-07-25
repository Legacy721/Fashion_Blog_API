package com.legacy.Fashion.blog.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {

    private String email;
    private String username;
    private String password;


}
