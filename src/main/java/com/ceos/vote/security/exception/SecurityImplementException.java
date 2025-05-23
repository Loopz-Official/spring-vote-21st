package com.ceos.vote.security.exception;


import com.ceos.vote.global.exception.CustomException;
import com.ceos.vote.global.exception.ErrorCode;

public class SecurityImplementException extends CustomException {
    public SecurityImplementException(ErrorCode errorCode) {
        super(errorCode);
    }

    public SecurityImplementException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
