package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.CategorizedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorizedItemRepository extends JpaRepository<CategorizedItem, CategorizedItem.Id> {
}
