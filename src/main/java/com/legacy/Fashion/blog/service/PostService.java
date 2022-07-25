package com.legacy.Fashion.blog.service;

import com.legacy.Fashion.blog.dto.PostDTO;
import com.legacy.Fashion.blog.model.Post;
import com.legacy.Fashion.blog.model.pageCriteria.PostPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {


    void createPost(PostDTO postDTO, Long categoryId);

    Page<Post> viewAllPosts(PostPage postPage);

    List<PostDTO> viewAllPostsByCategory(Long categoryId);


    Post viewPostById(Long postId);

    Post updatePost(Long postId, PostDTO postDto);

    void deletePost(Long postId);

}
