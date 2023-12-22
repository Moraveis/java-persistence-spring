package com.study.javapersistence.domain;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Embeddable
public class Address {

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false, length = 5)
    private String zipCode;

    @NotNull
    @Column(nullable = false)
    private String city;


    @ElementCollection
    @CollectionTable(name = "CONTACT", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "NAME", nullable = false)
    private Set<String> contacts = new HashSet<>();

    public Address() {
    }

    public Address(String street, String zipCode, String city, Set<String> contacts) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.contacts = contacts;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<String> getContacts() {
        return Collections.unmodifiableSet(contacts);
    }

    public void addContact(String contact) {
        contacts.add(contact);
    }
}
