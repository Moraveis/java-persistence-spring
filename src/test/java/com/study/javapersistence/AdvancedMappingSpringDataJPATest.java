package com.study.javapersistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdvancedMappingSpringDataJPATest {

    @Autowired
    private TestService service;

    @Test
    void testStoreLoadEntities() {
        service.storeLoadEntities();
    }
}
