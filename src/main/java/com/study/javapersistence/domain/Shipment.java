package com.study.javapersistence.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Shipment {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createdOn;

    @NotNull
    private ShipmentState shipmentState = ShipmentState.TRANSIT;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ITEM_SHIPMENT",
            joinColumns = @JoinColumn(name = "SHIPMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID", nullable = false, unique = true)
    )
    private Item auction;

    public Shipment() {
    }

    public Shipment(Item auction) {
        this.auction = auction;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ShipmentState getShipmentState() {
        return shipmentState;
    }

    public Item getAuction() {
        return auction;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setShipmentState(ShipmentState shipmentState) {
        this.shipmentState = shipmentState;
    }

    public void setAuction(Item auction) {
        this.auction = auction;
    }
}
