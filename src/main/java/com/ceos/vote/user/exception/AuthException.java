package com.ceos.vote.user.exception;


import com.ceos.vote.global.exception.CustomException;

public class AuthException extends CustomException {
    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);
    }

    public AuthException(AuthErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
