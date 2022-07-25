package com.legacy.Fashion.blog.service.serviceImpl;

import com.legacy.Fashion.blog.exceptions.AlreadyLikedException;
import com.legacy.Fashion.blog.exceptions.PostsNotFoundException;
import com.legacy.Fashion.blog.exceptions.UserNotFoundException;
import com.legacy.Fashion.blog.model.Like;
import com.legacy.Fashion.blog.model.Post;
import com.legacy.Fashion.blog.model.User;
import com.legacy.Fashion.blog.repository.LikeRepository;
import com.legacy.Fashion.blog.repository.PostRepository;
import com.legacy.Fashion.blog.service.LikeService;
import com.legacy.Fashion.blog.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    private final SessionUtils sessionUtils;



    @Override
    @Transactional
    public String likeAPost(Long postId) {

        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if(user == null) throw new UserNotFoundException("You are not logged in");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));

        if(likeRepository.likeExistByUserAndPost(user, post)) {
            throw new AlreadyLikedException("You have liked this post before");
        }
        else {
            Like like = Like.builder()
                    .posts(post)
                    .user(user)
                    .timeLiked(LocalDateTime.now())
                    .build();

            post.getLikedItems().add(like);
            postRepository.save(post);
        }

        return "Post liked";
    }

    @Override
    @Transactional
    public String unlikePost(Long postId) {
        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if(user == null) throw new UserNotFoundException("You are not logged in");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));

        likeRepository.deleteLikeByUserAndPosts(user, post);
        return "Post unliked";
    }
}
