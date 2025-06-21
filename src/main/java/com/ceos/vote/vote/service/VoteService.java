package com.ceos.vote.vote.service;

import com.ceos.vote.candidate.Repository.CandidateRepository;
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
    public CandidateResultResponse votePartLeader(Long userId, Long candidateId) {

        // 후보 존재 확인
        Candidate candidate = checkExistingCandidate(candidateId);
        // 파트장/데모데이 투표 타입 확인
        PartLeader partLeader = checkVoteTypeLeader(candidate);
        // 유저 정보 조회 + 파트 확인
        checkUserPartType(userId, partLeader);
        // 이미 투표한 파트장 후보가 있는지 확인
        checkDuplicatedVote(userId, CandidateType.PART_LEADER);

        // 투표 저장
        saveVoteResult(Vote.of(userId, partLeader));

        return getCandidateResultResponse(candidateId, partLeader);
    }


    @Transactional
    public CandidateResultResponse voteDemoday(Long userId, Long candidateId) {

        // 후보 존재 확인
        Candidate candidate = checkExistingCandidate(candidateId);
        Demoday demoday = checkVoteTypeDemoday(candidate);
        // 유저 exist 확인
        userService.findByUserId(userId);
        // 이미 투표한 데모데이 후보가 있는지 확인
        checkDuplicatedVote(userId, CandidateType.DEMODAY);

        // 투표 저장
        saveVoteResult(Vote.of(userId, candidate));

        return getCandidateResultResponse(voteConverter.toCandidateResponse(demoday), candidateId);
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


    private void checkDuplicatedVote(Long userId, CandidateType partLeader) {
        boolean alreadyVotedLeader = voteRepository.existsByUserIdAndCandidate_Type(userId, partLeader);
        if (alreadyVotedLeader) {
            throw new VoteException(ALREADY_VOTED);
        }
    }

    private void checkUserPartType(Long userId, PartLeader partLeader) {
        UserEntity user = userService.findByUserId(userId);

        if (!user.getPart().equals(partLeader.getPart())) {
            throw new VoteException(INVALID_VOTE_PART);
        }
    }


    private Candidate checkExistingCandidate(Long candidateId) {
        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new VoteException(CANDIDATE_NOT_FOUND));
    }

    private PartLeader checkVoteTypeLeader(Candidate candidate) {
        if (!(candidate instanceof PartLeader partLeader)) {
            throw new VoteException(INVALID_CANDIDATE_TYPE, "후보자가 파트장 후보가 아닙니다.");
        }
        return partLeader;
    }

    private CandidateResultResponse getCandidateResultResponse(Long candidateId, PartLeader partLeader) {
        return getCandidateResultResponse(voteConverter.toCandidateResponse(partLeader), candidateId);
    }

    private CandidateResultResponse getCandidateResultResponse(CandidateResponse voteConverter, Long candidateId) {
        Long voteCount = voteRepository.countByCandidateId(candidateId);
        return new CandidateResultResponse(voteConverter, voteCount);
    }

    private Demoday checkVoteTypeDemoday(Candidate candidate) {
        if (!(candidate instanceof Demoday demoday)) {
            throw new VoteException(INVALID_CANDIDATE_TYPE, "후보자가 데모데이 후보가 아닙니다.");
        }
        return demoday;
    }


    private void saveVoteResult(Vote vote) {
        voteRepository.save(vote);
    }

}


