package com.study.javapersistence;

import com.study.javapersistence.domain.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootTest
public class HelloWorldJPATest {

    @Test
    public void storeLoadMessageTest() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ch02");

        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            Message message = new Message();
            message.setText("Hello World!");

            em.persist(message);
            em.getTransaction().commit();

            em.getTransaction().begin();
            List<Message> messageList = em.createQuery("select m from Message m", Message.class).getResultList();
            messageList.get(messageList.size() - 1).setText("Hello World from JPA!");
            em.getTransaction().commit();

            Assertions.assertAll(
                    () -> Assertions.assertEquals(1, messageList.size()),
                    () -> Assertions.assertEquals("Hello World from JPA!", messageList.get(0).getText())
            );

            em.close();
        } finally {
            entityManagerFactory.close();
        }
    }
}
