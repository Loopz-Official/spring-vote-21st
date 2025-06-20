package com.ceos.vote.vote.service;

import com.ceos.vote.candidate.Repository.CandidateRepository;
import com.ceos.vote.candidate.Repository.PartLeaderRepository;
import com.ceos.vote.candidate.domain.Candidate;
import com.ceos.vote.candidate.domain.Demoday;
import com.ceos.vote.candidate.domain.PartLeader;
import com.ceos.vote.candidate.domain.enums.CandidateType;
import com.ceos.vote.user.domain.UserEntity;
import com.ceos.vote.user.service.UserService;
import com.ceos.vote.vote.Exception.VoteException;
import com.ceos.vote.vote.converter.VoteConverter;
import com.ceos.vote.vote.domain.Vote;
import com.ceos.vote.vote.dto.CandidateListResponse;
import com.ceos.vote.vote.dto.CandidateResponse;
import com.ceos.vote.vote.dto.CandidateResultListResponse;
import com.ceos.vote.vote.dto.CandidateResultResponse;
import com.ceos.vote.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.ceos.vote.vote.Exception.VoteErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserService userService;
    private final CandidateRepository candidateRepository;

    private final VoteConverter voteConverter;

    @Transactional
    public void votePartLeader(Long userId, Long candidateId) {

        // 후보 존재 확인
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new VoteException(CANDIDATE_NOT_FOUND));

        if (!(candidate instanceof PartLeader)) {
            throw new VoteException(INVALID_CANDIDATE_TYPE, "후보자가 파트장 후보가 아닙니다.");
        }

        PartLeader partLeader = (PartLeader) candidate;

        // 유저 정보 조회 + 파트 확인
        UserEntity user = userService.findByUserId(userId);

        if (!user.getPart().equals(partLeader.getPart())) {
            throw new VoteException(INVALID_VOTE_PART);
        }

        // 중복 투표 확인 개선
        List<Vote> allByUserId = voteRepository.findAllByUserId(userId);
        for (Vote vote : allByUserId) {
            Long voteCandidateId = vote.getCandidateId();
            candidateRepository.findById(voteCandidateId).ifPresent(
                    votedCandidate -> {
                        if (votedCandidate.getType().equals(CandidateType.PART_LEADER)) {
                            throw new VoteException(ALREADY_VOTED);
                        }
                    }
            );

        }
//        if (voteRepository.existsByUserId(userId)) {
//            throw new VoteException(ALREADY_VOTED);
//        }

        // 투표 저장
        Vote vote = Vote.builder()
                .userId(userId)
                .candidateId(candidateId)
                .build();

        voteRepository.save(vote);
    }


    @Transactional
    public void voteDemoday(Long userId, Long candidateId) {

        // 후보 존재 확인
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new VoteException(CANDIDATE_NOT_FOUND));

        if (!(candidate instanceof Demoday)) {
            throw new VoteException(INVALID_CANDIDATE_TYPE, "후보자가 데모데이 후보가 아닙니다.");
        }

        Demoday demoday = (Demoday) candidate;
        // 유저 정보 조회
        UserEntity user = userService.findByUserId(userId);

        // 중복 투표 확인 개선
        List<Vote> allByUserId = voteRepository.findAllByUserId(userId);
        for (Vote vote : allByUserId) {
            Long voteCandidateId = vote.getCandidateId();
            candidateRepository.findById(voteCandidateId).ifPresent(
                    votedCandidate -> {
                        if (votedCandidate.getType().equals(CandidateType.DEMODAY)) {
                            throw new VoteException(ALREADY_VOTED);
                        }
                    }
            );
        }
        // 투표 저장
        Vote vote = Vote.builder()
                .userId(userId)
                .candidateId(candidateId)
                .build();
        voteRepository.save(vote);
    }

    public CandidateListResponse getCandidates() {

        List<PartLeader> leaderCandidates =
                candidateRepository.findAllByType(CandidateType.PART_LEADER);

        List<CandidateResponse> responseList = leaderCandidates.stream()
                .map(voteConverter::toCandidateResponse)
                .toList();

        return new CandidateListResponse(CandidateType.PART_LEADER, responseList);
    }

    public CandidateListResponse getDemodayCandidates() {

        List<Demoday> demodayCandidates =
                candidateRepository.findAllDemodayByType(CandidateType.DEMODAY);


        List<CandidateResponse> responseList = demodayCandidates.stream()
                .map(voteConverter::toCandidateResponse)
                .toList();

        return new CandidateListResponse(CandidateType.DEMODAY, responseList);
    }

    public CandidateResultListResponse getCandidateResult() {

        List<CandidateResponse> candidates = getCandidates().candidates();
        List<CandidateResultResponse> list = candidates.stream()
                .map(candidateResponse -> {
                    Long candidateId = candidateResponse.id();
                    Long voteCount = voteRepository.countByCandidateId(candidateId);
                    return new CandidateResultResponse(candidateResponse, voteCount);
                })
                .toList();

        return new CandidateResultListResponse(CandidateType.PART_LEADER, list);
    }

    public CandidateResultListResponse getDemodayCandidateResult() {

        List<CandidateResponse> candidates = getDemodayCandidates().candidates();
        List<CandidateResultResponse> list = candidates.stream()
                .map(candidateResponse -> {
                    Long candidateId = candidateResponse.id();
                    Long voteCount = voteRepository.countByCandidateId(candidateId);
                    return new CandidateResultResponse(candidateResponse, voteCount);
                })
                .toList();

        return new CandidateResultListResponse(CandidateType.DEMODAY, list);
    }

}


