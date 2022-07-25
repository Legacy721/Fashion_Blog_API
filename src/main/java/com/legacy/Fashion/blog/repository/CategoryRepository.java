package com.legacy.Fashion.blog.repository;


import com.legacy.Fashion.blog.model.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<PostCategory, Long> {

    boolean existsByCategoryName(String category);
}
