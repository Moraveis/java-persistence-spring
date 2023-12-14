package com.study.javapersistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryResultsTest extends JavaPersistenceWithHibernateApplicationTests {

    @Test
    void testFindByArrayAndSort() {
        List<Object[]> usersList1 = userRepository.findByAsArrayAndSort("ar", Sort.by("username"));
        List<Object[]> usersList2 = userRepository.findByAsArrayAndSort("ar", Sort.by("email_result").descending());
        List<Object[]> usersList3 = userRepository.findByAsArrayAndSort("ar", JpaSort.unsafe("length(u.email)"));

        assertAll(
                () -> assertEquals(2, usersList1.size()),
                () -> assertEquals("darren", usersList1.get(0)[0]),
                () -> assertEquals(21, usersList1.get(0)[1]),
                () -> assertEquals(2, usersList2.size()),
                () -> assertEquals("marion", usersList2.get(0)[0]),
                () -> assertEquals(26, usersList2.get(0)[1]),
                () -> assertEquals(2, usersList3.size()),
                () -> assertEquals("darren", usersList2.get(0)[0]),
                () -> assertEquals(21, usersList2.get(0)[1])
        );
    }


}
