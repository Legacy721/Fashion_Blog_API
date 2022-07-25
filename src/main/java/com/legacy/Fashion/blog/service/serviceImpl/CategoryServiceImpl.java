package com.legacy.Fashion.blog.service.serviceImpl;

import com.legacy.Fashion.blog.dto.CategoryDTO;
import com.legacy.Fashion.blog.enums.Role;
import com.legacy.Fashion.blog.exceptions.CategoryAlreadyExistException;
import com.legacy.Fashion.blog.exceptions.CategoryNotFoundException;
import com.legacy.Fashion.blog.exceptions.EmptyListException;
import com.legacy.Fashion.blog.exceptions.PermissionDeniedException;
import com.legacy.Fashion.blog.model.PostCategory;
import com.legacy.Fashion.blog.model.User;
import com.legacy.Fashion.blog.repository.CategoryRepository;
import com.legacy.Fashion.blog.service.CategoryService;
import com.legacy.Fashion.blog.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    private final SessionUtils sessionUtils;



    @Override
    public PostCategory createCategory(PostCategory category) {
        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if (!user.getRole().equals(Role.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        if(categoryRepository.existsByCategoryName(category.getCategoryName()))
            throw new CategoryAlreadyExistException("This category already exists");

        PostCategory categoryEntity = PostCategory.builder()
                .categoryName(category.getCategoryName())
                .build();
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public List<PostCategory> viewCategories() {
        List<PostCategory> allCategories = categoryRepository.findAll();
        if(allCategories.isEmpty()) throw new EmptyListException(" You have not created any category");
        return allCategories;
    }

    @Override
    public PostCategory updateCategory(Long categoryId, CategoryDTO categoryDto) {
        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if (!user.getRole().equals(Role.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        PostCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));
        if(Objects.nonNull(categoryDto.getCategoryName()) && !"".equalsIgnoreCase(categoryDto.getCategoryName()))
            BeanUtils.copyProperties(categoryDto, category);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if (!user.getRole().equals(Role.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        PostCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));

        categoryRepository.delete(category);

    }

    @Override
    public PostCategory fetchCategory(Long categoryId) {
        Long id = sessionUtils.getLoggedInUser();
        User user = sessionUtils.findUserById(id);

        if (!user.getRole().equals(Role.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        Optional<PostCategory> category = categoryRepository.findById(categoryId);
        if(category.isPresent())
            return category.get();
        throw new CategoryNotFoundException("This category is not available");
    }
}
