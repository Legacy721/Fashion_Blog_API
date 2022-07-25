package com.legacy.Fashion.blog.exceptions;

public class PostsNotFoundException extends RuntimeException {
    public PostsNotFoundException(String s) {
        super(s);
    }
}
