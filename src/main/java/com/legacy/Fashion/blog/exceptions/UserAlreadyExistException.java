package com.legacy.Fashion.blog.exceptions;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String s) {
        super(s);
    }
}
