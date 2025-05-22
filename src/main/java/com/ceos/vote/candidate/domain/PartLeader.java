package com.ceos.vote.candidate.domain;
import com.ceos.vote.candidate.domain.enums.Part;
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
}
