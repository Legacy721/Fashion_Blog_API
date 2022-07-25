package com.legacy.Fashion.blog.service.serviceImpl;

import com.legacy.Fashion.blog.dto.CommentDTO;
import com.legacy.Fashion.blog.enums.Role;
import com.legacy.Fashion.blog.exceptions.CommentNotFoundException;
import com.legacy.Fashion.blog.exceptions.PermissionDeniedException;
import com.legacy.Fashion.blog.exceptions.PostsNotFoundException;
import com.legacy.Fashion.blog.exceptions.UserNotFoundException;
import com.legacy.Fashion.blog.model.Comment;
import com.legacy.Fashion.blog.model.Post;
import com.legacy.Fashion.blog.model.User;
import com.legacy.Fashion.blog.repository.CommentRepository;
import com.legacy.Fashion.blog.repository.PostRepository;
import com.legacy.Fashion.blog.service.CommentService;
import com.legacy.Fashion.blog.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final SessionUtils sessionUtils;




    @Override
    public void makeComment(Long postId, Comment comment) {

        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if(!user.getRole().equals(Role.FOLLOWER))
            throw new PermissionDeniedException("You do not have the permission to access this page");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));

        Comment createComment = Comment.builder()
                .description(comment.getDescription())
                .user(user)
                .posts(post)
                .build();
        commentRepository.save(createComment);

        post.getComments().add(comment);
        postRepository.save(post);

    }


    @Override
    public Comment updateComment(Long commentId, CommentDTO commentDTO) {
        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if(!user.getRole().equals(Role.FOLLOWER))
            throw new PermissionDeniedException("You do not have the permission to access this page");

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("This comment is not available"));

        if(Objects.nonNull(commentDTO.getDescription()) && !commentDTO.getDescription().equals("")){
            BeanUtils.copyProperties(commentDTO, comment);
        }

        return commentRepository.save(comment);
    }

    @Override
    public String deleteComment(Long commentId) {
        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if (user == null){
            throw new UserNotFoundException("You are not logged in");
        }

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new CommentNotFoundException("Comment not found!"));

        commentRepository.delete(comment);

        return "Comment deleted successfully!";
    }

    @Override
    public Comment viewCommentById(Long postId) {
        Comment comment = commentRepository.findById(postId)
                .orElseThrow(()-> new CommentNotFoundException("Comment not found!"));

        return comment;
    }

    @Override
    public List<CommentDTO> viewAllCommentByPost(Long postId) {
        Post posts = postRepository.findById(postId)
                .orElseThrow(() ->new PostsNotFoundException("This post is not available"));

        List<Comment> comments = posts.getComments();
        List<CommentDTO> commentDTOs = new ArrayList<>();

        for(Comment comment : comments){
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);

            commentDTOs.add(commentDTO);
        }
        return commentDTOs;
    }
}
