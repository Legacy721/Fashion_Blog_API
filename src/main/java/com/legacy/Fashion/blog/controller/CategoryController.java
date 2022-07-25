package com.legacy.Fashion.blog.controller;


import com.legacy.Fashion.blog.dto.CategoryDTO;
import com.legacy.Fashion.blog.model.PostCategory;
import com.legacy.Fashion.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/categories/")
public class CategoryController {

    private  final CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<PostCategory> createCategory(@RequestBody PostCategory category){
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping("/viewAParticularCategory/{id}")
    public ResponseEntity<PostCategory> viewACategory(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.fetchCategory(categoryId));
    }

    @GetMapping("/viewAllCategories")
    public  ResponseEntity<List<PostCategory>> viewAllCategories(){
        return ResponseEntity.ok(categoryService.viewCategories());
    }

    @PatchMapping("/modifyCategory/{id}")
    public ResponseEntity<PostCategory> updateCategory(@PathVariable("id")
                                                           Long categoryId, @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDTO));
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.ACCEPTED);
    }
}
