package com.dev.securityreview.domain.user.repository;

import com.dev.securityreview.domain.user.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
