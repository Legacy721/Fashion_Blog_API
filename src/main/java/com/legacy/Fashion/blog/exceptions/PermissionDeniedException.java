package com.legacy.Fashion.blog.exceptions;

public class PermissionDeniedException extends RuntimeException{
    public PermissionDeniedException(String s) {
        super(s);
    }
}
