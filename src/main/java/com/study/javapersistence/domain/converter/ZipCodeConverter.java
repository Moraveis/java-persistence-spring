package com.study.javapersistence.domain.converter;

import com.study.javapersistence.domain.GermanZipCode;
import com.study.javapersistence.domain.SwissZipcode;
import com.study.javapersistence.domain.ZipCode;

import javax.persistence.AttributeConverter;

public class ZipCodeConverter implements AttributeConverter<ZipCode, String> {

    @Override
    public String convertToDatabaseColumn(ZipCode zipCode) {
        return zipCode.getValue();
    }

    @Override
    public ZipCode convertToEntityAttribute(String s) {
        if (s.length() == 5) {
            return new GermanZipCode(s);
        } else if (s.length() == 4) {
            return new SwissZipcode(s);
        }

        throw new IllegalArgumentException("Unsupported zipcode in database: " + s);
    }
}
