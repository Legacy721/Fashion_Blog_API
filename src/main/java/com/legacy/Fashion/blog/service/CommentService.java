package com.legacy.Fashion.blog.service;

import com.legacy.Fashion.blog.dto.CommentDTO;
import com.legacy.Fashion.blog.model.Comment;

import java.util.List;

public interface CommentService {

    void makeComment(Long postId, Comment comment);



    Comment updateComment(Long commentId, CommentDTO commentDTO);

    String deleteComment(Long commentId);

    Comment viewCommentById(Long postId);

    List<CommentDTO> viewAllCommentByPost(Long postId);
}
