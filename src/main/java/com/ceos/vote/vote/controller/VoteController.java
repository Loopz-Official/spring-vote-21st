package com.ceos.vote.vote.controller;

import com.ceos.vote.security.dto.PrincipalUserDetails;
import com.ceos.vote.vote.dto.VoteRequestDto;
import com.ceos.vote.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote/v1")
@RequiredArgsConstructor
@Slf4j
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/partLeader")
    public ResponseEntity<Void> votePartLeader(@AuthenticationPrincipal User currentUser,
                                       @RequestBody VoteRequestDto request) {

        String userId = currentUser.getUsername();

        voteService.votePartLeader(Long.parseLong(userId), request.candidateId());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
