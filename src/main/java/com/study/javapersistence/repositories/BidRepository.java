package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.Bid;
import com.study.javapersistence.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BidRepository extends JpaRepository<Bid, Long> {

    Set<Bid> findByItem(Item item);
}
