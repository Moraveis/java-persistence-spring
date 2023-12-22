package com.study.javapersistence.domain;

import org.hibernate.annotations.OrderBy;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @MapKeyColumn(name = "FILENAME")
    @Column(name = "IMAGENAME")
    @OrderBy(clause = "FILENAME DESC")
    private Map<String, String> images = new LinkedHashMap<>();

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getImages() {
        return Collections.unmodifiableMap(images);
    }

    public void putImage(String key, String value) {
        images.put(key, value);
    }

}
