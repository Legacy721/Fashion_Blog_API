package com.legacy.Fashion.blog.service.serviceImpl;

import com.legacy.Fashion.blog.dto.PostDTO;
import com.legacy.Fashion.blog.enums.Role;
import com.legacy.Fashion.blog.exceptions.CategoryNotFoundException;
import com.legacy.Fashion.blog.exceptions.EmptyListException;
import com.legacy.Fashion.blog.exceptions.PermissionDeniedException;
import com.legacy.Fashion.blog.exceptions.PostsNotFoundException;
import com.legacy.Fashion.blog.model.Post;
import com.legacy.Fashion.blog.model.PostCategory;
import com.legacy.Fashion.blog.model.User;
import com.legacy.Fashion.blog.model.pageCriteria.PostPage;
import com.legacy.Fashion.blog.repository.CategoryRepository;
import com.legacy.Fashion.blog.repository.PostRepository;
import com.legacy.Fashion.blog.service.PostService;
import com.legacy.Fashion.blog.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;

    private final SessionUtils sessionUtils;






    @Override
    public void createPost(PostDTO postDto, Long categoryId) {

        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if(!user.getRole().equals(Role.ADMIN))
            throw new PermissionDeniedException("You do not have the permission to access this page");
        PostCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));
        Post post = Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .category(category)
                .likedItems(new ArrayList<>())
                .comments(new ArrayList<>())
                .user(user)
                .build();
        postRepository.save(post);

        category.getPosts().add(post);
        categoryRepository.save(category);

    }

    @Override
    public Page<Post> viewAllPosts(PostPage postPage) {

        Sort sort = Sort.by(postPage.getSortDirection(), postPage.getSortBy());
        Pageable pageable = PageRequest.of(postPage.getPageNumber(), postPage.getPageSize(), sort);
        Page<Post> posts = postRepository.findAll(pageable);
        return posts;
    }

    @Override
    public List<PostDTO> viewAllPostsByCategory(Long categoryId) {
        PostCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));

        List<Post> posts = category.getPosts();
        List<PostDTO> postDTOs = new ArrayList<>();

        for(Post post : posts){
            PostDTO userPostDTO = new PostDTO();
            BeanUtils.copyProperties(post, userPostDTO);

            postDTOs.add(userPostDTO);
        }

        return postDTOs;
    }

    @Override
    public Post viewPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));
    }

    @Override
    public Post updatePost(Long postId, PostDTO postDTO) {
        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if(!user.getRole().equals(Role.ADMIN))
            throw new PermissionDeniedException("You do not have the permission to access this page");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));

        if(Objects.nonNull(postDTO.getTitle()) && !"".equalsIgnoreCase(postDTO.getTitle()))
            BeanUtils.copyProperties(postDTO, post);
        if(Objects.nonNull(postDTO.getDescription()) && !"".equalsIgnoreCase(postDTO.getDescription()))
            BeanUtils.copyProperties(postDTO, post);
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {

        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if(!user.getRole().equals(Role.ADMIN))
            throw new PermissionDeniedException("You do not have the permission to access this page");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));
        postRepository.delete(post);

    }

}
