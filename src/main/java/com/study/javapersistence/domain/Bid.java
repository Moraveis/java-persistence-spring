package com.study.javapersistence.domain;

public class Bid {

    private Item item;

    public Bid(Item item) {
        this.item = item;
        item.getBids().add(this);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
