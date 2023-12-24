package com.study.javapersistence;

import com.study.javapersistence.configurations.SpringDataConfiguration;
import com.study.javapersistence.domain.Address;
import com.study.javapersistence.domain.User;
import com.study.javapersistence.repositories.AddressRepository;
import com.study.javapersistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(value = SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class AdvancedMappingSpringDataJPATest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void storeLoadEntities() {
        User john = new User("John Smith");
        Address address = new Address("Boston", "Flowers Street", "012345");

        john.setShippingAddress(address);

        userRepository.save(john);

        User user = userRepository.findById(john.getId()).get();
        Address address2 = addressRepository.findById(address.getId()).get();

        assertAll(
                () -> assertEquals("Flowers Street", user.getShippingAddress().getStreet()),
                () -> assertEquals("012345", user.getShippingAddress().getZipCode()),
                () -> assertEquals("Boston", user.getShippingAddress().getCity()),
                () -> assertEquals("Flowers Street", address2.getStreet()),
                () -> assertEquals("012345", address2.getZipCode()),
                () -> assertEquals("Boston", address2.getCity())
        );
    }
}
