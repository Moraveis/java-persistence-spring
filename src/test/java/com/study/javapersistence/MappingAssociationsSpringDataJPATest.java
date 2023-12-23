package com.study.javapersistence;

import com.study.javapersistence.domain.Bid;
import com.study.javapersistence.domain.Item;
import com.study.javapersistence.repositories.BidRepository;
import com.study.javapersistence.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MappingAssociationsSpringDataJPATest {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Test
    void storeLoadEntities() {

        Item item = new Item("Foo");

        Bid bid = new Bid(BigDecimal.valueOf(100), item);
        Bid bid2 = new Bid(BigDecimal.valueOf(200), item);

        item.addBid(bid);
        item.addBid(bid2);

        itemRepository.save(item);

        List<Item> items = itemRepository.findAll();
        Set<Bid> bids = bidRepository.findByItem(item);

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals(2, bids.size())
        );

        Item retrievedItem = itemRepository.findById(item.getId()).get();

        for (Bid someBid : bidRepository.findByItem(retrievedItem)) {
            bidRepository.delete(someBid);
        }

        itemRepository.delete(retrievedItem);

        List<Item> items2 = itemRepository.findAll();
        Set<Bid> bids2 = bidRepository.findByItem(item);

        assertAll(
                () -> assertEquals(1, items2.size()),
                () -> assertEquals(2, bids2.size())
        );

    }
}
