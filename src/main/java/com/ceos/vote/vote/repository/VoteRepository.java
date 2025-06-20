package com.ceos.vote.vote.repository;

import com.ceos.vote.vote.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByUserId(Long userId);
    List<Vote> findAllByUserId(Long userId);
    Long countByCandidateId(Long candidateId);
}
