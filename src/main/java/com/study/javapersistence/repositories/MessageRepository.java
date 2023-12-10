package com.study.javapersistence.repositories;

import com.study.javapersistence.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
