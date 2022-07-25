package com.legacy.Fashion.blog.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String s) {
        super(s);
    }
}
