package com.study.javapersistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @AttributeOverride(name = "name", column = @Column(name = "CITY", nullable = false))
    private City city;

}


