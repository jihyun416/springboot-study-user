package com.jessy.user.repository;

import com.jessy.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom, RevisionRepository<User, String, Long> {
    Optional<User> findByUserIdEquals(String userId);
}
