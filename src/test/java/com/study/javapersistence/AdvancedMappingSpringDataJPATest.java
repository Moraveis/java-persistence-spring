package com.study.javapersistence;

import com.study.javapersistence.domain.Address;
import com.study.javapersistence.domain.CategorizedItem;
import com.study.javapersistence.domain.Category;
import com.study.javapersistence.domain.Item;
import com.study.javapersistence.domain.Shipment;
import com.study.javapersistence.domain.User;
import com.study.javapersistence.repositories.BidRepository;
import com.study.javapersistence.repositories.CategoryRepository;
import com.study.javapersistence.repositories.ItemRepository;
import com.study.javapersistence.repositories.ShipmentRepository;
import com.study.javapersistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Test
    public void storeLoadEntities() {

        Item someItem = new Item("Some Item");
        itemRepository.save(someItem);
        Item otherItem = new Item("Other Item");
        itemRepository.save(otherItem);

        User someUser = new User("John Smith");
        Address deliveryAddress = new Address("Flowers Street", "01246", "Boston");
        someUser.setShippingAddress(deliveryAddress);
        someUser.getBoughtItems().add(someItem); // Link
        someItem.setBuyer(someUser); // Link
        someUser.getBoughtItems().add(otherItem);
        otherItem.setBuyer(someUser);
        userRepository.save(someUser);

        Shipment firstShipment = new Shipment();
        deliveryAddress.addDelivery(firstShipment);
        shipmentRepository.save(firstShipment);

        Shipment secondShipment = new Shipment();
        deliveryAddress.addDelivery(secondShipment);
        shipmentRepository.save(secondShipment);

        Item unsoldItem = new Item("Unsold Item");
        itemRepository.save(unsoldItem);

        Item item = itemRepository.findById(someItem.getId()).get();
        Item item2 = itemRepository.findById(unsoldItem.getId()).get();
        User johnsmith = userRepository.findById(someUser.getId()).get();

        assertAll(
                () -> assertEquals("John Smith", item.getBuyer().getUsername()),
                () -> assertTrue(item.getBuyer().getBoughtItems().contains(item)),
                () -> assertNull(item2.getBuyer()),
                () -> assertEquals(2, johnsmith.getShippingAddress().getDeliveries().size())
        );

    }

    @Test
    @Transactional
    public void storeLoadCategoriesEntities() {
        Category someCategory = new Category("Some Category");
        Category otherCategory = new Category("Other Category");

        categoryRepository.save(someCategory);
        categoryRepository.save(otherCategory);

        Item someItem = new Item("Some Item");
        Item otherItem = new Item("Other Item");

        itemRepository.save(someItem);
        itemRepository.save(otherItem);

        User someUser = new User("John Smith");
        userRepository.save(someUser);

        CategorizedItem linkOne = new CategorizedItem(someUser, someItem);
        someCategory.addCategorizedItem(linkOne);

        CategorizedItem linkTwo = new CategorizedItem(someUser, otherItem);
        someCategory.addCategorizedItem(linkTwo);

        CategorizedItem linkThree = new CategorizedItem(someUser, someItem);
        otherCategory.addCategorizedItem(linkThree);

        Category category1 = categoryRepository.findById(someCategory.getId()).get();
        Category category2 = categoryRepository.findById(otherCategory.getId()).get();

        Item item1 = itemRepository.findById(someItem.getId()).get();
        User john = userRepository.findById(someUser.getId()).get();

        List<Category> categoriesOfItem = categoryRepository.findCategoryWithCategorizedItems(item1);

        assertAll(
                () -> assertEquals(2, category1.getCategorizedItems().size()),
                () -> assertEquals(1, category2.getCategorizedItems().size()),
                () -> assertEquals(item1, category2.getCategorizedItems().iterator().next().getItem()),
                () -> assertEquals(john, category2.getCategorizedItems().iterator().next().getAddedBy()),
                () -> assertEquals(2, categoriesOfItem.size())
        );
    }
}
