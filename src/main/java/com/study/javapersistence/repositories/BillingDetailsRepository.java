package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingDetailsRepository<T extends BillingDetails, ID> extends JpaRepository<T, ID> {

    List<T> findByOwner(String owner);
}
