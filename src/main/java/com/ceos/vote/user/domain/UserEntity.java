package com.ceos.vote.user.domain;

import com.ceos.vote.candidate.domain.enums.Part;
import com.ceos.vote.candidate.domain.enums.Team;
import com.ceos.vote.global.domain.BaseTimeEntityWithDeletion;
import com.ceos.vote.user.dto.request.RequestJoin;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import static com.ceos.vote.user.domain.Role.*;
import static jakarta.persistence.EnumType.*;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "`user`")
@Getter
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE `user` SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class UserEntity extends BaseTimeEntityWithDeletion {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //가입할 때 작성할 ID
    @Column(nullable = false, unique = true, length = 100)
    private String loginId;

    private String realName;

    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Part part;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Team team;

    @Enumerated(STRING)
    private Role role;

    private boolean isDeleted;
    private boolean isEnabled;


    public static UserEntity of(String loginId, String realName, String password, String email, Part part, Team team) {
        return UserEntity.builder()
                .loginId(loginId)
                .realName(realName)
                .password(password)
                .email(email)
                .part(part)
                .team(team)
                .build();
    }

    public static UserEntity from(RequestJoin requestJoin, String encodedPassword) {
        return UserEntity.builder()
                .loginId(requestJoin.email())
                .realName(requestJoin.realName())
                .password(encodedPassword)
                .email(requestJoin.email())
                .part(requestJoin.part())
                .team(requestJoin.team())
                .role(USER)
                .build();
    }

    @Builder(access = PRIVATE)
    private UserEntity(String loginId, String realName, String password, String email, Part part, Team team, Role role) {

        this.isDeleted = false;
        this.isEnabled = true;

        this.role = role;
        this.loginId = loginId;
        this.realName = realName;
        this.password = password;
        this.email = email;
        this.part = part;
        this.team = team;
    }
}
