package com.zipdoridle.zachwitip.auth.model.repository;


import com.zipdoridle.zachwitip.auth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long userId);
    Optional<User> findByOauthId(String oauthId);
}
