package com.ceos.vote.user.controller;

import com.ceos.vote.user.dto.UserDto;
import com.ceos.vote.user.dto.request.RequestJoin;
import com.ceos.vote.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/join")
    public ResponseEntity<UserDto> join(@RequestBody @Valid RequestJoin requestJoin) {
        UserDto result = userService.join(requestJoin);
        return ResponseEntity.status(CREATED).body(result);
    }



}