package com.ceos.vote.vote.converter;

import com.ceos.vote.candidate.domain.Candidate;
import com.ceos.vote.candidate.domain.Demoday;
import com.ceos.vote.candidate.domain.PartLeader;
import com.ceos.vote.vote.dto.CandidateListResponse;
import com.ceos.vote.vote.dto.CandidateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = IGNORE
)
public interface VoteConverter {

    VoteConverter INSTANCE = Mappers.getMapper(VoteConverter.class);


    List<CandidateResponse> toCandidateResponseList(List<Candidate> leaderCandidates);

    CandidateResponse toCandidateResponse(PartLeader candidate);
    CandidateResponse toCandidateResponse(Demoday candidate);
}
