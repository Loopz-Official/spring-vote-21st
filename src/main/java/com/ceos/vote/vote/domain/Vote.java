package com.ceos.vote.vote.domain;

import com.ceos.vote.candidate.domain.Candidate;
import com.ceos.vote.global.domain.BaseEntity;
import com.ceos.vote.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity voter;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

}
