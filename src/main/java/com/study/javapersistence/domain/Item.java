package com.study.javapersistence.domain;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Type;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @Column(name = "FILENAME")
    @GenericGenerator(name = "sequence_gen", strategy = "sequence")
    @CollectionId(column = @Column(name = "IMAGE_ID"), type = @Type(type = "long"), generator = "sequence_gen")
    @OrderBy(clause = "FILENAME DESC")
    private Collection<String> images = new ArrayList<>();

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

    public Collection<String> getImages() {
        return Collections.unmodifiableCollection(images);
    }

    public void addImage(String image) {
        images.add(image);
    }

}
