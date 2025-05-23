package com.ceos.vote.user.dto.request;

import com.ceos.vote.candidate.domain.enums.Part;
import com.ceos.vote.candidate.domain.enums.Team;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestJoin(

        @NotBlank
        String loginId,
        @NotBlank
        String password,
        @NotBlank
        @Email
        String email,
        @NotBlank
        Part part,
        @NotBlank
        String realName,
        @NotBlank
        Team team
) {
}
