package com.study.javapersistence;

import com.study.javapersistence.domain.Bid;
import com.study.javapersistence.domain.Item;
import com.study.javapersistence.domain.ItemBidSummary;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemBidSummaryTest {

    @Test
    public void itemBidSummaryTest() {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ch05.subselect");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Item item = new Item();
            item.setName("Some Item");
            item.setAuctionEnd(Helper.tomorrow());

            Bid bid1 = new Bid(new BigDecimal(1000.0), item);
            Bid bid2 = new Bid(new BigDecimal(1100.0), item);

            em.persist(item);
            em.persist(bid1);
            em.persist(bid2);

            em.getTransaction().commit();
            em.getTransaction().begin();

            TypedQuery<ItemBidSummary> query =
                    em.createQuery("select ibs from ItemBidSummary ibs where ibs.itemId = :id",
                            ItemBidSummary.class);
            ItemBidSummary itemBidSummary =
                    query.setParameter("id", 1000L).getSingleResult();

            assertAll(
                    () -> assertEquals(1000, itemBidSummary.getItemId()),
                    () -> assertEquals("Some Item", itemBidSummary.getName()),
                    () -> assertEquals(2, itemBidSummary.getNumberOfBids())
            );

            em.getTransaction().commit();

        } finally {
            em.close();
            emf.close();
        }
    }
}
