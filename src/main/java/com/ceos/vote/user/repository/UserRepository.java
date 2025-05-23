package com.ceos.vote.user.repository;

import com.ceos.vote.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
