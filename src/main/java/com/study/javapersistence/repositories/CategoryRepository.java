package com.study.javapersistence.repositories;

import com.study.javapersistence.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
