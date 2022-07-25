package com.legacy.Fashion.blog.dto;


import com.legacy.Fashion.blog.model.Post;
import com.legacy.Fashion.blog.model.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class LikeDTO implements Serializable {
    private Post posts;
    private User user;
}
