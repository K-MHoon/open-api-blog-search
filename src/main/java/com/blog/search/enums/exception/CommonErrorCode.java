package com.blog.search.enums.exception;

import com.blog.search.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 파라미터가 포함되어 있습니다."),
    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST, "제한된 범위를 벗어 났습니다."),
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "예상된 곳 외의 예외가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
