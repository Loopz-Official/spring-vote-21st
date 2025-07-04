package com.ceos.vote.redis.service;

import com.ceos.vote.redis.entity.RefreshToken;
import com.ceos.vote.redis.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenRedisService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(Long userId, String refreshToken, Long ttl) {

        RefreshToken token = RefreshToken.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .ttl(ttl)
                .build();

        refreshTokenRepository.save(token);
    }

    public Optional<RefreshToken> findRefreshToken(Long userId) {
        return refreshTokenRepository.findById(userId);
    }

    public void deleteRefreshToken(Long userId) {
        refreshTokenRepository.deleteById(userId);
    }

}