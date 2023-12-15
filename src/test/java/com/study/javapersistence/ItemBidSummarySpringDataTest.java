package com.study.javapersistence;

import com.study.javapersistence.domain.Bid;
import com.study.javapersistence.domain.Item;
import com.study.javapersistence.domain.ItemBidSummary;
import com.study.javapersistence.repositories.BidRepository;
import com.study.javapersistence.repositories.ItemBidSummaryRepository;
import com.study.javapersistence.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemBidSummarySpringDataTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ItemBidSummaryRepository itemBidSummaryRepository;

    @Test
    public void itemBidSummaryTest() {

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(Helper.tomorrow());

        Bid bid1 = new Bid(new BigDecimal(1000.0), item);
        Bid bid2 = new Bid(new BigDecimal(1100.0), item);

        itemRepository.save(item);
        bidRepository.save(bid1);
        bidRepository.save(bid2);

        Optional<ItemBidSummary> itemBidSummary = itemBidSummaryRepository.findById(1000L);

        assertAll(
                () -> assertEquals(1000, itemBidSummary.get().getItemId()),
                () -> assertEquals("Some Item", itemBidSummary.get().getName()),
                () -> assertEquals(2, itemBidSummary.get().getNumberOfBids())
        );

    }
}
