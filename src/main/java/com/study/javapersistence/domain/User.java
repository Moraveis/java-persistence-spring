package com.study.javapersistence.domain;

import com.study.javapersistence.domain.converter.ZipCodeConverter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    private String username;

    @Convert(
            converter = ZipCodeConverter.class,
            attributeName = "city.zipCode"
    )
    private Address homeAddress;

    @Embedded
    @AttributeOverride(name = "street", column = @Column(name = "BILLING_STREET"))
    @AttributeOverride(name = "city.zipCode", column = @Column(name = "BILLING_ZIPCODE", length = 5))
    @AttributeOverride(name = "city.name", column = @Column(name = "BILLING_CITY"))
    @AttributeOverride(name = "city.country", column = @Column(name = "BILLING_COUNTRY"))
    @Convert(
            converter = ZipCodeConverter.class,
            attributeName = "city.zipCode"
    )
    private Address billingAddress;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
}
