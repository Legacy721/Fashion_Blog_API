package com.legacy.Fashion.blog.repository;

import com.legacy.Fashion.blog.enums.Role;
import com.legacy.Fashion.blog.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
//@AutoConfigureTestDatabase
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TestEntityManager entityManager;


    UserRepositoryTest(){

    }

    @BeforeEach
    void setUp() {

        User user = User.builder()
                .username("obeme")
                .email("obeme@gmail.com")
                .password("123456")
                .build();
        entityManager.persist(user);
    }

    @Test
    public void whenFindById_thenReturnUser(){
        User user = userRepository.findById(1L).get();
        assertEquals(user.getUsername(), "obeme");

    }
}