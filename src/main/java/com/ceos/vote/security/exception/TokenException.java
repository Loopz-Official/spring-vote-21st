package com.ceos.vote.security.exception;


import com.ceos.vote.global.exception.CustomException;

public class TokenException extends CustomException {

    public TokenException(SecurityErrorCode errorCode) {
        super(errorCode);
    }

    public TokenException(SecurityErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
