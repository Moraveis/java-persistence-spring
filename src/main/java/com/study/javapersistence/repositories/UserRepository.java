package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
