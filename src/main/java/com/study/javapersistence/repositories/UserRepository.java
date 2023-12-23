package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u inner join fetch u.bids where u.id = :id")
    User findUserWithBids(@Param("id") Long id);
}
