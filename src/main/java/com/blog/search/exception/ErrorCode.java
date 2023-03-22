package com.blog.search.exception;

import org.springframework.http.HttpStatus;

/**
 * 예외 처리 관련 Interface
 */
public interface ErrorCode {

    String name();
    HttpStatus getHttpStatus();
    String getMessage();
}
