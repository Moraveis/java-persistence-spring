package com.study.javapersistence.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

public class Projection {

    public interface UserSummary {
        String getUsername();

        @Value("#{target.username} #{target.email}")
        String getInfo();
    }

    @RequiredArgsConstructor
    @Getter
    public static class UsernameOnly {
        final String username;
    }
}
