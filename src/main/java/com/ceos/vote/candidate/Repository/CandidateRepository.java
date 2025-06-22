package com.ceos.vote.candidate.Repository;

import com.ceos.vote.candidate.domain.Candidate;
import com.ceos.vote.candidate.domain.Demoday;
import com.ceos.vote.candidate.domain.PartLeader;
import com.ceos.vote.candidate.domain.enums.CandidateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<PartLeader> findAllByType(CandidateType type);
    List<Demoday> findAllDemodayByType(CandidateType type);
}

