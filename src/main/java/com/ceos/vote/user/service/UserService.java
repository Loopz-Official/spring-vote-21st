package com.ceos.vote.user.service;

import com.ceos.vote.user.domain.UserEntity;
import com.ceos.vote.user.dto.UserDto;
import com.ceos.vote.user.dto.request.RequestJoin;
import com.ceos.vote.user.exception.AuthException;
import com.ceos.vote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ceos.vote.user.exception.AuthErrorCode.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDto join(RequestJoin requestJoin) {

        String encoded = passwordEncoder.encode(requestJoin.password());
        UserEntity user = UserEntity.from(requestJoin, encoded);

        userRepository.save(user);

        return UserDto.from(user);
    }


    public UserEntity findByUserId(Long userId) {
        System.out.println("UserService findByUserId called with userId = " + userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new AuthException(USER_NOT_FOUND, String.format("User with ID %s not found", userId)));
    }
}
