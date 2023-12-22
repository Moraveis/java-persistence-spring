package com.study.javapersistence.domain;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    private Map<FileName, Image> images = new HashMap<>();

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

    public Map<FileName, Image> getImages() {
        return Collections.unmodifiableMap(images);
    }

    public void putImage(FileName key, Image value) {
        images.put(key, value);
    }

}
