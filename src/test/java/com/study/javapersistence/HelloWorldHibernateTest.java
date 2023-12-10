package com.study.javapersistence;

import com.study.javapersistence.domain.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class HelloWorldHibernateTest {

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Message.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void storeLoadMessage() {
        try (SessionFactory sessionFactory = createSessionFactory(); Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Message message = new Message();
            message.setText("Hello World from Hibernate!");

            session.persist(message);
            session.getTransaction().commit();

            session.beginTransaction();
            CriteriaQuery<Message> criteriaQuery = session.getCriteriaBuilder().createQuery(Message.class);
            criteriaQuery.from(Message.class);

            List<Message> messages = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();

            Assertions.assertAll(
                    () -> Assertions.assertEquals(1, messages.size()),
                    () -> Assertions.assertEquals("Hello World from Hibernate!", messages.get(0).getText())
            );
        }
    }
}
