package com.study.javapersistence;

import com.study.javapersistence.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

public class QueryByExampleTest extends JavaPersistenceWithHibernateApplicationTests {

    @Test
    void testEmailWithQueryByExample() {
        User user = new User();
        user.setEmail("@someotherdomain.com");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active")
                .withMatcher("email", match -> match.endsWith());

        Example<User> example = Example.of(user, matcher);

        List<User> users = userRepository.findAll(example);

        Assertions.assertEquals(4, users.size());
    }

    @Test
    void testUsernameWithQueryByExample() {
        User user = new User();
        user.setUsername("J");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active")
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                .withIgnoreCase();

        Example<User> example = Example.of(user, matcher);

        List<User> users = userRepository.findAll(example);

        Assertions.assertEquals(3, users.size());
    }
}
