package com.legacy.Fashion.blog.repository;

import com.legacy.Fashion.blog.model.Like;
import com.legacy.Fashion.blog.model.Post;
import com.legacy.Fashion.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT COUNT(lp) > 0 FROM Like lp WHERE lp.user = :user AND lp.posts = :post")
    boolean likeExistByUserAndPost(User user, Post post);

    @Modifying
    @Query("DELETE FROM Like lp WHERE lp.user = :user AND lp.posts = :post")
    void deleteLikeByUserAndPosts(User user, Post post);
}
