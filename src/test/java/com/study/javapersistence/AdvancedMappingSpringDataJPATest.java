package com.study.javapersistence;

import com.study.javapersistence.domain.Item;
import com.study.javapersistence.domain.Shipment;
import com.study.javapersistence.repositories.ItemRepository;
import com.study.javapersistence.repositories.ShipmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class AdvancedMappingSpringDataJPATest {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void testStoreLoadEntities() {

        Shipment shipment = new Shipment();
        shipment.setCreatedOn(LocalDateTime.now());
        shipmentRepository.save(shipment);

        Item item = new Item("Foo");
        itemRepository.save(item);

        Shipment auctionShipment = new Shipment(item);
        auctionShipment.setCreatedOn(LocalDateTime.now());
        shipmentRepository.save(auctionShipment);

        Item item2 = itemRepository.findById(item.getId()).get();
        Shipment shipment2 = shipmentRepository.findById(shipment.getId()).get();
        Shipment auctionShipment2 = shipmentRepository.findShipmentWithItem(auctionShipment.getId());

        Assertions.assertAll(
                () -> assertNull(shipment2.getAuction()),
                () -> assertEquals(item2.getId(), auctionShipment2.getAuction().getId()),
                () -> assertEquals(item2.getName(), auctionShipment2.getAuction().getName())
        );

    }
}
