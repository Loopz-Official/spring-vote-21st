package com.ceos.vote.security.handler;

import com.ceos.vote.global.dto.CommonResponse;
import com.ceos.vote.security.jwt.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    private final RedisTemplate<String, String> redisTemplate;
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    @Override

    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String accessToken = authHeader.substring(7);

            String userId = jwtProvider.getSubject(accessToken);

            redisTemplate.delete("refreshToken:" + userId);
        }

        response.setStatus(HttpServletResponse.SC_OK);

        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Logout success")
                .data(null)
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(commonResponse));
    }
}
