package com.study.javapersistence;

import com.study.javapersistence.domain.Message;
import com.study.javapersistence.repositories.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HelloWorldSpringDataJPATest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void storeLoadMessage() {
        Message message = new Message();
        message.setText("Hello World from Spring Data JPA!");

        messageRepository.save(message);

        List<Message> messages = (List<Message>) messageRepository.findAll();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, messages.size()),
                () -> Assertions.assertEquals("Hello World from Spring Data JPA!", messages.get(0).getText())
        );
    }
}
