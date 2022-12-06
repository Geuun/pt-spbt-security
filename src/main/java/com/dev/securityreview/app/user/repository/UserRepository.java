package com.dev.securityreview.app.user.repository;

import com.dev.securityreview.app.user.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 회원가입 로직
    Optional<User> findByUserName(String userName);
}
