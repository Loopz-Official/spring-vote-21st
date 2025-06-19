package com.ceos.vote.vote.Exception;

import com.ceos.vote.global.exception.CustomException;
import com.ceos.vote.global.exception.ErrorCode;

public class VoteException extends CustomException {

    public VoteException(ErrorCode errorCode) {
        super(errorCode);
    }

    public VoteException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
