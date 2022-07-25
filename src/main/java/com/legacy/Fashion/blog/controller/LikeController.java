package com.legacy.Fashion.blog.controller;


import com.legacy.Fashion.blog.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/likePost/{id}")
    public ResponseEntity<String> likeAPost(@PathVariable("id") Long postId){
        return ResponseEntity.ok(likeService.likeAPost(postId));
    }



    @DeleteMapping("/unlikePost/{id}")
    public ResponseEntity<String> unlikePost(@PathVariable ("id") Long postId){
        return ResponseEntity.ok(likeService.unlikePost(postId));
    }
}
