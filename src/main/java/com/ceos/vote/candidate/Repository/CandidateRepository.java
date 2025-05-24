package com.ceos.vote.candidate.Repository;

import com.ceos.vote.candidate.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}

