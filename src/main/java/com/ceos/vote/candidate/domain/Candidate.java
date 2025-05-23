package com.ceos.vote.candidate.domain;

import com.ceos.vote.global.domain.BaseEntity;
import com.ceos.vote.vote.domain.Vote;
import com.ceos.vote.candidate.domain.enums.CandidateType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Candidate extends BaseEntity {
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