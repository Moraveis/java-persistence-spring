package com.study.javapersistence;

import com.study.javapersistence.domain.User;
import com.study.javapersistence.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class JavaPersistenceWithHibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaPersistenceWithHibernateApplication.class, args);
    }

    @Bean
    public ApplicationRunner configure(UserRepository userRepository) {
        return env -> {
            User userBeth = new User("beth", LocalDate.of(2020, Month.AUGUST, 3));
            User userMike = new User("mike", LocalDate.of(2020, Month.JANUARY, 18));

            userRepository.save(userBeth);
            userRepository.save(userMike);

            userRepository.findAll().forEach(System.out::println);
        };
    }

}
