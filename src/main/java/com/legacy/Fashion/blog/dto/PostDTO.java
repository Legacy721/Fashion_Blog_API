package com.legacy.Fashion.blog.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    private String title;
    private String description;
    private int likes;
    private int comments;
}
