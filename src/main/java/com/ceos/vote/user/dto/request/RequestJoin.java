package com.ceos.vote.user.dto.request;

import com.ceos.vote.candidate.domain.enums.Part;
import com.ceos.vote.candidate.domain.enums.Team;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestJoin(

        @NotBlank
        String loginId,
        @NotBlank
        String password,
        @NotBlank
        @Email
        String email,
        @NotNull
        Part part,
        @NotBlank
        String realName,
        @NotNull
        Team team
) {
}
