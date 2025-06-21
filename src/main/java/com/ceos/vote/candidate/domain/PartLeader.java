package com.ceos.vote.candidate.domain;
import com.ceos.vote.candidate.domain.enums.Part;
import com.ceos.vote.candidate.domain.enums.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartLeader extends Candidate {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Part part;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Team team;
}
