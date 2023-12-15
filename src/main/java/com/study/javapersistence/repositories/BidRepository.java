package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, Long> {
}
