package com.legacy.Fashion.blog.controller;


import com.legacy.Fashion.blog.dto.CommentDTO;
import com.legacy.Fashion.blog.model.Comment;
import com.legacy.Fashion.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/makeComment/{id}")
    public ResponseEntity<String> makeCommentToAPost(@PathVariable("id") Long postId, @RequestBody Comment comment){
        commentService.makeComment(postId, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("/updateComment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable ("id") Long commentId,
                                                 @RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(commentService.updateComment(commentId, commentDTO));
    }


    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable ("id") Long commentId){
        return  ResponseEntity.ok(commentService.deleteComment(commentId));
    }

}
