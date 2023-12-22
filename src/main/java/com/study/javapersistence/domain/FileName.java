package com.study.javapersistence.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FileName {

    @Column(nullable = false)
    private String name;
}
