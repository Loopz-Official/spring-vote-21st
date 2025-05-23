package com.ceos.vote.candidate.domain;

import com.ceos.vote.candidate.domain.enums.CandidateType;
import com.ceos.vote.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Candidate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CandidateType type;

    //득표수
    private int voteCount;

}