package com.legacy.Fashion.blog.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "post_tbl")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 10000)
    private String description;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;


    @ManyToOne(optional = false)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    @JsonIgnore
    private User user;

    @ManyToOne(
            optional = false
    )
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "categoryId"
    )
    @JsonIgnore
    private PostCategory category;

    @OneToMany(
            mappedBy = "posts",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Like> likedItems;

    @OneToMany(
            mappedBy = "posts",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Comment> comments;
}
