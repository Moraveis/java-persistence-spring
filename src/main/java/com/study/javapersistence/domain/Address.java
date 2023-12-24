package com.study.javapersistence.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

    @Id
    @GeneratedValue(generator = "addressKeyGenerator")
    @GenericGenerator(
            name = "addressKeyGenerator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    private Long id;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String zipCode;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private User user;

    public Address() {
    }

    public Address(User user) {
        this.user = user;
    }

    public Address(User user, String city, String street, String zipCode) {
        this.user = user;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
