package com.legacy.Fashion.blog.service;

import com.legacy.Fashion.blog.dto.CategoryDTO;
import com.legacy.Fashion.blog.model.PostCategory;

import java.util.List;

public interface CategoryService {

    PostCategory createCategory(PostCategory category);

    List<PostCategory> viewCategories();

    PostCategory updateCategory(Long categoryId, CategoryDTO categoryDto);

    void deleteCategory(Long categoryId);

    PostCategory fetchCategory(Long categoryId);
}
