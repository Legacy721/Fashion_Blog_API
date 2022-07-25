package com.legacy.Fashion.blog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.legacy.Fashion.blog.dto.LikeDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "like_tbl")
@IdClass(LikeDTO.class)
public class Like implements Serializable {


    @Id
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "post_id",
            referencedColumnName = "postId"
    )
    private Post posts;

    @Id
    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    private  User user;

    @CreationTimestamp
    private LocalDateTime timeLiked;
}
