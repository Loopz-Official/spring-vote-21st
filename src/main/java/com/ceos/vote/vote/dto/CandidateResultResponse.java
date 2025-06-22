package com.ceos.vote.vote.dto;

public record CandidateResultResponse(
        CandidateResponse candidate,
        Long voteCount
) {
}
