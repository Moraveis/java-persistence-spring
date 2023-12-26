package com.study.javapersistence;

import com.study.javapersistence.domain.Item;
import com.study.javapersistence.domain.User;
import com.study.javapersistence.repositories.BidRepository;
import com.study.javapersistence.repositories.ItemRepository;
import com.study.javapersistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AdvancedMappingSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    public void storeLoadEntities() {

        Item someItem = new Item("Some Item");
        itemRepository.save(someItem);
        Item otherItem = new Item("Other Item");
        itemRepository.save(otherItem);

        User someUser = new User("John Smith");
        someUser.getBoughtItems().add(someItem); // Link
        someItem.setBuyer(someUser); // Link
        someUser.getBoughtItems().add(otherItem);
        otherItem.setBuyer(someUser);
        userRepository.save(someUser);

        Item unsoldItem = new Item("Unsold Item");
        itemRepository.save(unsoldItem);

        Item item = itemRepository.findById(someItem.getId()).get();
        Item item2 = itemRepository.findById(unsoldItem.getId()).get();

        assertAll(
                () -> assertEquals("John Smith", item.getBuyer().getUsername()),
                () -> assertTrue(item.getBuyer().getBoughtItems().contains(item)),
                () -> assertNull(item2.getBuyer())
        );

    }
}
