package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Iterable<Item> findByMetricWeight(double weight);
}
