package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.CreditCard;

import java.util.List;

public interface CreditCardRepository extends BillingDetailsRepository<CreditCard, Long> {

    List<CreditCard> findByExpYear(String expYear);
}
