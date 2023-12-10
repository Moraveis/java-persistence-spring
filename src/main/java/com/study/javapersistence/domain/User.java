package com.study.javapersistence.domain;

import java.util.StringTokenizer;

public class User {

    private String firstName;
    private String lastName;

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String name) {
        StringTokenizer tokenizer = new StringTokenizer(name);
        this.firstName = tokenizer.nextToken();
        this.lastName = tokenizer.nextToken();
    }
}
