package com.legacy.Fashion.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment_tbl")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, length = 10000)
    private String description;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;


    @ManyToOne(optional = false)
   // @JsonIgnore
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    private User user;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "post_id",
            referencedColumnName = "postId"
    )
    private Post posts;
}
