package com.study.javapersistence.ch02.repositories;

import com.study.javapersistence.ch02.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
