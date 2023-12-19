package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.BankAccount;

import java.util.List;

public interface BankAccountRepository extends BillingDetailsRepository<BankAccount, Long> {

    List<BankAccount> findBySwift(String swift);
}
