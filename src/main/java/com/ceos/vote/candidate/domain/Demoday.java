package com.ceos.vote.candidate.domain;

import com.ceos.vote.candidate.domain.enums.Team;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Demoday extends Candidate {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Team team;
}
