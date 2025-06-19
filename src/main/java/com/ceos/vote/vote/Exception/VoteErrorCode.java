package com.ceos.vote.vote.Exception;

import com.ceos.vote.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum VoteErrorCode implements ErrorCode {

    CANDIDATE_NOT_FOUND(HttpStatus.BAD_REQUEST, "후보자가 존재하지 않습니다."),
    ALREADY_VOTED(HttpStatus.BAD_REQUEST, "이미 투표 완료하였습니다."),
    INVALID_VOTE_PART(HttpStatus.BAD_REQUEST, "본인 파트의 파트장만 투표 가능합니다."),
    INVALID_CANDIDATE_TYPE(HttpStatus.BAD_REQUEST, "파트장이 아닙니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
