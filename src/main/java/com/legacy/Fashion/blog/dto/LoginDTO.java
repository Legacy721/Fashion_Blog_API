package com.legacy.Fashion.blog.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    private String email;
    private String password;

}
