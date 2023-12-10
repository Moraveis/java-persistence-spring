package com.study.javapersistence.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Item {

    private Set<Bid> bids = new HashSet<>();

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public void addBid(Bid bid) {
        if (bid == null) {
            throw new NullPointerException("Can't add null Bid");
        }
        if (bid.getItem() != null) {
            throw new IllegalStateException("Bid is already assigned to an Item");
        }
        bids.add(bid);
        bid.setItem(this);
    }
}
