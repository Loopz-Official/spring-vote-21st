package com.ceos.vote.vote.domain;

import com.ceos.vote.candidate.domain.Candidate;
import com.ceos.vote.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidate;


    public static Vote of(Long userId, Candidate candidate) {
        return Vote.builder()
                .userId(userId)
                .candidate(candidate)
                .build();
    }

}
