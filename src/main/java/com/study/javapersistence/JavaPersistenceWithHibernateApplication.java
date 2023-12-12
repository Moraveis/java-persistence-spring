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

}
