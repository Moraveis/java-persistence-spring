package com.study.javapersistence.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @NotNull
    private String name;

    @OneToMany
    @JoinColumn(
            name = "ITEM_ID",
            nullable = false
    )
    @OrderColumn(
            name = "BID_POSITION", // Defaults to BIDS_ORDER
            nullable = false
    )
    private List<Bid> bids = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ITEM_BUYER",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(nullable = false)
    )
    private User buyer;

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

    public void setName(String name) {
        this.name = name;
    }

    public List<Bid> getBids() {
        return Collections.unmodifiableList(bids);
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
