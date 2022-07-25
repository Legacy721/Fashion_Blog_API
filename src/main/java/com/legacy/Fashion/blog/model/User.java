package com.legacy.Fashion.blog.model;


import com.legacy.Fashion.blog.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_tbl")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @ToString.Exclude
    private Long userId;

    @Column(unique = true, nullable = false, length = 30)
    @ToString.Exclude
    private String email;

    @Column(nullable = false, length = 10)
    @ToString.Exclude
    private String password;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ToString.Exclude
    private Role role;


}
