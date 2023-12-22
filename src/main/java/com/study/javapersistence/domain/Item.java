package com.study.javapersistence.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @AttributeOverride(name = "filename", column = @Column(name = "FNAME", nullable = false))
    private Set<Image> images = new HashSet<>();

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

    public Set<Image> getImages() {
        return Collections.unmodifiableSet(images);
    }

    public void addImage(Image image) {
        images.add(image);
    }

}
