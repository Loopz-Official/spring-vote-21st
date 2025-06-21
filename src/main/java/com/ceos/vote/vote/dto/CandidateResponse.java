package com.ceos.vote.vote.dto;

import com.ceos.vote.candidate.domain.enums.CandidateType;
import com.ceos.vote.candidate.domain.enums.Part;
import com.ceos.vote.candidate.domain.enums.Team;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CandidateResponse(
        // 공통
        Long id,

        // 파트리더
        Part part,
        String name,

        // 데모데이
        Team team

) {
}
