package com.legacy.Fashion.blog.exceptions;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String s) {
        super(s);
    }
}
