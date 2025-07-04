package com.ceos.vote.vote.repository;

import com.ceos.vote.candidate.domain.enums.CandidateType;
import com.ceos.vote.vote.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByUserId(Long userId);
    boolean existsByUserIdAndCandidate_Type(Long userId, CandidateType candidateType);
    Long countByCandidateId(Long candidateId);
}
