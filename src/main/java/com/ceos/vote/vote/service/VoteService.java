package com.ceos.vote.vote.service;

import com.ceos.vote.candidate.Repository.CandidateRepository;
import com.ceos.vote.candidate.domain.Candidate;
import com.ceos.vote.candidate.domain.PartLeader;
import com.ceos.vote.user.domain.UserEntity;
import com.ceos.vote.user.service.UserService;
import com.ceos.vote.vote.Exception.VoteException;
import com.ceos.vote.vote.domain.Vote;
import com.ceos.vote.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ceos.vote.vote.Exception.VoteErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserService userService;
    private final CandidateRepository candidateRepository;

    @Transactional
    public void votePartLeader(Long userId, Long candidateId) {

        // 후보 존재 확인
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new VoteException(CANDIDATE_NOT_FOUND));

        if (!(candidate instanceof PartLeader)) {
            throw new VoteException(INVALID_CANDIDATE_TYPE);
        }

        PartLeader partLeader = (PartLeader) candidate;

        // 유저 정보 조회 + 파트 확인
        UserEntity user = userService.findByUserId(userId);

        if (!user.getPart().equals(partLeader.getPart())) {
            throw new VoteException(INVALID_VOTE_PART);
        }

        // 중복 투표 확인
        if (voteRepository.existsByUserId(userId)) {
            throw new VoteException(ALREADY_VOTED);
        }

        // 투표 저장
        Vote vote = Vote.builder()
                .userId(userId)
                .candidateId(candidateId)
                .build();

        voteRepository.save(vote);
    }
}