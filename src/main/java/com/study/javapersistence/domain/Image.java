package com.study.javapersistence.domain;

import org.hibernate.annotations.Parent;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Image {

    @Column(nullable = false)
    private String fileName;

    private int width;
    private int height;

    @Parent
    private Item item;

    public Image(String fileName, int width, int height) {
        this.fileName = fileName;
        this.width = width;
        this.height = height;
    }

    public Image() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return width == image.width && height == image.height && Objects.equals(fileName, image.fileName) && Objects.equals(item, image.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, width, height, item);
    }
}
