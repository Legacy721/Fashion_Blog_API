package com.legacy.Fashion.blog.controller;


import com.legacy.Fashion.blog.dto.CommentDTO;
import com.legacy.Fashion.blog.dto.PostDTO;
import com.legacy.Fashion.blog.model.Post;
import com.legacy.Fashion.blog.model.pageCriteria.PostPage;
import com.legacy.Fashion.blog.service.CommentService;
import com.legacy.Fashion.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adminPost")
public class PostController {

    private final PostService postService;

    private final CommentService commentService;


    @PostMapping("/createPostByCategoryId/{id}")
    public String createNewPost(@PathVariable("id") Long categoryId, @RequestBody PostDTO postDTO){
        postService.createPost(postDTO, categoryId);
        return "Post created";
    }


    @GetMapping("/viewAllPosts")
    public ResponseEntity<Page<Post>> viewAllPosts(PostPage postPage){

        return ResponseEntity.ok(postService.viewAllPosts(postPage));
    }


    @GetMapping("/viewPostById/{id}")
    public ResponseEntity<Post> viewPostById(@PathVariable ("id") Long postId){
        return ResponseEntity.ok(postService.viewPostById(postId));
    }

    @GetMapping("/viewPostByCategory/{id}")
    public ResponseEntity<List<PostDTO>> viewAllPostByCategory(@PathVariable ("id") Long categoryId){
        return ResponseEntity.ok(postService.viewAllPostsByCategory(categoryId));
    }

    @PostMapping("/updatePost/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable ("id") Long postId, @RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.updatePost(postId, postDTO));
    }

    @DeleteMapping("/deletePost/{id}")
    public String deletePost(@PathVariable ("id") Long postId){
        postService.deletePost(postId);
        return "Post Successfully deleted";
    }

    @GetMapping("/viewAllCommentInAPost/{id}")
    public ResponseEntity<List<CommentDTO>> viewAllCommentByPost(@PathVariable ("id") Long postId){
        return ResponseEntity.ok(commentService.viewAllCommentByPost(postId));
    }

}
