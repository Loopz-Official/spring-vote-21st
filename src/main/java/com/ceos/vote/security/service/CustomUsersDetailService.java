package com.ceos.vote.security.service;

import com.ceos.vote.security.dto.PrincipalUserDetails;
import com.ceos.vote.user.domain.UserEntity;
import com.ceos.vote.user.exception.AuthException;
import com.ceos.vote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.ceos.vote.user.exception.AuthErrorCode.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class CustomUsersDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new AuthException(USER_NOT_FOUND));

        return new PrincipalUserDetails(userEntity);
    }
}
