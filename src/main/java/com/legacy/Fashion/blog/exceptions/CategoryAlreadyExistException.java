package com.legacy.Fashion.blog.exceptions;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException(String s ) {
        super(s);
    }
}
