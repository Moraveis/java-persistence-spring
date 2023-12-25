package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
