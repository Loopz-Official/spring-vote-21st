package com.ceos.vote.user.repository;

import com.ceos.vote.user.domain.UserEntity;
import io.jsonwebtoken.security.Jwks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

}
