package com.legacy.Fashion.blog.exceptions;

public class InvalidLoginDetailsException extends RuntimeException {
    public InvalidLoginDetailsException(String s) {
        super(s);
    }
}
