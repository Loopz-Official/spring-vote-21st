package com.ceos.vote.security.handler;

import com.ceos.vote.global.dto.CommonResponse;
import com.ceos.vote.redis.service.RefreshTokenRedisService;
import com.ceos.vote.security.constants.SecurityConstants;
import com.ceos.vote.security.dto.PrincipalUserDetails;
import com.ceos.vote.security.jwt.JwtProvider;
import com.ceos.vote.user.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.ceos.vote.security.constants.SecurityConstants.*;
import static org.springframework.http.HttpHeaders.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRedisService refreshTokenRedisService;
    @Value("${jwt.expiration.refresh}")
    private Long refreshTokenExpiration;
    private final ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String userId = findUserIdFromAuthentication(authentication);

        String accessToken = jwtProvider.generateAccessToken(authentication, userId);
        String refreshToken = jwtProvider.generateRefreshToken(authentication, userId);

        refreshTokenRedisService.saveRefreshToken(Long.parseLong(userId), refreshToken, refreshTokenExpiration);

        String bearerAccess = TOKEN_PREFIX + accessToken;
        response.setHeader(AUTHORIZATION, bearerAccess);
        writeBodyWithUserDto(response, authentication);
    }

    private String findUserIdFromAuthentication(Authentication authentication) {
        PrincipalUserDetails principal = (PrincipalUserDetails) authentication.getPrincipal();
        return principal.getUserEntity().getId().toString();
    }

    private void writeBodyWithUserDto(HttpServletResponse response, Authentication authentication) throws IOException {
        UserDto user = UserDto.from(authentication);
        CommonResponse<UserDto> common = CommonResponse.<UserDto>builder()
                .status(HttpServletResponse.SC_OK)
                .message("OK")
                .data(user)
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(common));
    }
}
