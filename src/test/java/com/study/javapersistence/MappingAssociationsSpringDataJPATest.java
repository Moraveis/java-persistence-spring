package com.study.javapersistence;

import com.study.javapersistence.domain.Bid;
import com.study.javapersistence.domain.Item;
import com.study.javapersistence.domain.User;
import com.study.javapersistence.repositories.BidRepository;
import com.study.javapersistence.repositories.ItemRepository;
import com.study.javapersistence.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Test
    void storeLoadEntities() {

        User john = new User("John Smith");
        userRepository.save(john);

        Item item = new Item("Foo");

        Bid bid = new Bid(BigDecimal.valueOf(100), item);
        Bid bid2 = new Bid(BigDecimal.valueOf(200), item);
        item.addBid(bid);
        bid.setBidder(john);
        item.addBid(bid2);
        bid2.setBidder(john);

        itemRepository.save(item);

        List<Item> items = itemRepository.findAll();
        Set<Bid> bids = bidRepository.findByItem(item);
        User user = userRepository.findUserWithBids(john.getId());

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals(2, bids.size()),
                () -> assertEquals(2, user.getBids().size())
        );

        Item item1 = itemRepository.findItemWithBids(item.getId());
        Bid firstBid = item1.getBids().iterator().next();
        item1.removeBid(firstBid);

        itemRepository.save(item1);

        List<Item> items2 = itemRepository.findAll();
        List<Bid> bids2 = bidRepository.findAll();


        assertAll(
                () -> assertEquals(1, items2.size()),
                () -> assertEquals(1, bids2.size()),
                () -> assertEquals(2, user.getBids().size())
                //FAILURE
                //() -> assertEquals(1, user.getBids().size())
        );
    }
}
