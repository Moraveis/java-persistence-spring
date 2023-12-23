package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
