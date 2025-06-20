package com.ceos.vote.vote.dto;

import com.ceos.vote.candidate.domain.enums.CandidateType;

import java.util.List;

public record CandidateResultListResponse(
        CandidateType candidateType,
        List<CandidateResultResponse> results
) {
}
