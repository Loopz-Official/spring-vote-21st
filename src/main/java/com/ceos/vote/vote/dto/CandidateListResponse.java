package com.ceos.vote.vote.dto;

import com.ceos.vote.candidate.domain.enums.CandidateType;

import java.util.List;

public record CandidateListResponse(
        CandidateType candidateType,
        List<CandidateResponse> candidates
) {
}
