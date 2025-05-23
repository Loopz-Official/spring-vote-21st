package com.ceos.vote.security.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RequestLogin(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
