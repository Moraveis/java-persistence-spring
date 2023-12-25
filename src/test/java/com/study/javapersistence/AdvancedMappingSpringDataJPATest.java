package com.study.javapersistence;

import com.study.javapersistence.domain.Bid;
import com.study.javapersistence.domain.Item;
import com.study.javapersistence.repositories.BidRepository;
import com.study.javapersistence.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AdvancedMappingSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Test
    void testStoreLoadEntities() {

        Item item = new Item("Foo");
        itemRepository.save(item);

        Bid someBid = new Bid(new BigDecimal("123.00"), item);
        item.addBid(someBid);
        item.addBid(someBid);
        bidRepository.save(someBid);

        Item item2 = itemRepository.findItemWithBids(item.getId());

        assertAll(
                () -> assertEquals(2, item.getBids().size()),
                () -> assertEquals(1, item2.getBids().size())
        );

        Bid bid = new Bid(new BigDecimal("456.00"), item);
        item.addBid(bid); // No SELECT!
        bidRepository.save(bid);

        Item item3 = itemRepository.findItemWithBids(item.getId());

        assertEquals(2, item3.getBids().size());

    }
}
