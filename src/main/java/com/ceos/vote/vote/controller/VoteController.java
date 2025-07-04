package com.ceos.vote.vote.controller;

import com.ceos.vote.vote.dto.*;
import com.ceos.vote.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;

@RestController
@RequestMapping("/vote/v1")
@RequiredArgsConstructor
@Slf4j
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/leader")
    public ResponseEntity<CandidateResultResponse> votePartLeader(
            @AuthenticationPrincipal User currentUser,
            @RequestBody VoteRequestDto request)
    {

        String userId = currentUser.getUsername();
        log.info("Authenticated userId: {}", userId);

        CandidateResultResponse response
                = voteService.votePartLeader(Long.parseLong(userId), request.candidateId());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/demoday")
    public ResponseEntity<CandidateResultResponse> voteDemoday(
            @AuthenticationPrincipal User currentUser,
            @RequestBody VoteRequestDto request
    ) {

        String userId = currentUser.getUsername();
        log.info("Authenticated userId: {}", userId);

        CandidateResultResponse response
                = voteService.voteDemoday(Long.parseLong(userId), request.candidateId());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/leader/candidates")
    public ResponseEntity<CandidateListResponse> getCandidates() {

        CandidateListResponse response = voteService.getCandidates();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/leader/candidates/result")
    public ResponseEntity<CandidateResultListResponse> getCandidateResult() {

        CandidateResultListResponse response = voteService.getCandidateResult();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/demoday/candidates")
    public ResponseEntity<CandidateListResponse> getDemodayCandidates() {

        CandidateListResponse response = voteService.getDemodayCandidates();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/demoday/candidates/result")
    public ResponseEntity<CandidateResultListResponse> getDemodayCandidateResult() {

        CandidateResultListResponse response = voteService.getDemodayCandidateResult();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
