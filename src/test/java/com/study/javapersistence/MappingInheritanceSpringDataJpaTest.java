package com.study.javapersistence;

import com.study.javapersistence.domain.BankAccount;
import com.study.javapersistence.domain.CreditCard;
import com.study.javapersistence.repositories.BankAccountRepository;
import com.study.javapersistence.repositories.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MappingInheritanceSpringDataJpaTest {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Test
    void storeLoadEntities() {
        BankAccount bankAccount = new BankAccount("Mike Johnson", "12345", "Delta Bank", "BANKXY12");
        CreditCard creditCard = new CreditCard("John Smith", "123456789", "10", "2030");

        bankAccountRepository.save(bankAccount);
        creditCardRepository.save(creditCard);

        List<CreditCard> creditCardsByOwnerList = creditCardRepository.findByOwner("John Smith");
        List<BankAccount> bankAccountsByOwnerList = bankAccountRepository.findByOwner("Mike Johnson");
        List<CreditCard> creditCardsByExpYear = creditCardRepository.findByExpYear("2030");
        List<BankAccount> bankAccountsBySwift = bankAccountRepository.findBySwift("BANKXY12");

        assertAll(
                () -> assertEquals(1, creditCardsByOwnerList.size()),
                () -> assertEquals("123456789", creditCardsByOwnerList.get(0).getCardNumber()),
                () -> assertEquals(1, bankAccountsByOwnerList.size()),
                () -> assertEquals("12345", bankAccountsByOwnerList.get(0).getAccount()),
                () -> assertEquals(1, creditCardsByExpYear.size()),
                () -> assertEquals("John Smith", creditCardsByExpYear.get(0).getOwner()),
                () -> assertEquals(1, bankAccountsBySwift.size()),
                () -> assertEquals("Mike Johnson", bankAccountsBySwift.get(0).getOwner())
        );
    }
}
