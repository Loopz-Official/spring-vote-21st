package com.ceos.vote.user.dto;

import com.ceos.vote.candidate.domain.enums.Part;
import com.ceos.vote.candidate.domain.enums.Team;
import com.ceos.vote.security.dto.PrincipalUserDetails;
import com.ceos.vote.user.domain.UserEntity;
import org.springframework.security.core.Authentication;

public record UserDto(
        String email,
        String realName,
        String loginId,
        Part part,
        Team team
) {

    public static UserDto from(UserEntity user) {
        return new UserDto(
                user.getEmail(),
                user.getRealName(),
                user.getLoginId(),
                user.getPart(),
                user.getTeam()
        );
    }

    public static UserDto from(Authentication authentication) {
        PrincipalUserDetails principalUserDetails = (PrincipalUserDetails) authentication.getPrincipal();
        UserEntity user = principalUserDetails.getUserEntity();
        return new UserDto(
                user.getEmail(),
                user.getRealName(),
                user.getLoginId(),
                user.getPart(),
                user.getTeam()
        );
    }
}
