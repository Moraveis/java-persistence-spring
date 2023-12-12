package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findAllByOrderByUsernameAsc();

    List<User> findAllByRegistrationDateBetween(LocalDate start, LocalDate end);

    List<User> findByUsernameAndEmail(String username, String email);

    List<User> findByUsernameOrEmail(String username, String email);

    List<User> findByUsernameIgnoreCase(String username);

    List<User> findByLevelOrderByUsernameDesc(int level);

    List<User> findByLevelGreaterThanEqual(int level);

    List<User> findByUsernameContaining(String text);

    List<User> findByUsernameLike(String text);

    List<User> findByUsernameStartingWith(String start);

    List<User> findByUsernameEndingWith(String end);

    List<User> findByActive(boolean active);

    List<User> findByRegistrationDateIn(Collection<LocalDate> dates);

    List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);

}
