package com.study.javapersistence.domain;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

    /*
      The <code>Item</code> entity defaults to field access, the <code>@Id</code> is on a field.
   */
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    /*
       The <code>@Access(AccessType.PROPERTY)</code> setting on the <code>name</code> field switches this
       particular property to runtime access through getter/setter methods by the JPA provider.
    */
    @Access(AccessType.PROPERTY)
    @Column(name = "ITEM_NAME")
    private String name;

    @Transient
    private Set<Bid> bids = new HashSet<>();

    @Lob
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING) // Defaults to ORDINAL
    private AuctionType auctionType = AuctionType.HIGHEST_BID;

    @Formula("CONCAT(SUBSTR(DESCRIPTION, 1, 12), '...')")
    private String shortDescription;

    @Formula("(SELECT AVG(B.AMOUNT) FROM BID B WHERE B.ITEM_ID = ID)")
    private BigDecimal averageBidAmount;

    @Column(name = "IMPERIALWEIGHT")
    @ColumnTransformer(
            read = "IMPERIALWEIGHT / 2.20462",
            write = "? * 2.20462"
    )
    private double metricWeight;

    @CreationTimestamp
    private LocalDate createdOn;

    @UpdateTimestamp
    private LocalDateTime lastModified;

    @NotNull
    @org.hibernate.annotations.Type(
            type = "monetary_amount_eur"
    )
    @org.hibernate.annotations.Columns(columns = {
            @Column(name = "INITIALPRICE_AMOUNT"),
            @Column(name = "INITIALPRICE_CURRENCY", length = 3)
    })
    private MonetaryAmount initialPrice;

    @Future
    private Date auctionEnd;

    @Lob
    private java.sql.Blob image;

    @Type(type = "yes_no")
    private boolean verified = false;

    @NotNull
    @org.hibernate.annotations.Type(
            type = "monetary_amount_usd"
    )
    @org.hibernate.annotations.Columns(columns = {
            @Column(name = "BUYNOWPRICE_AMOUNT"),
            @Column(name = "BUYNOWPRICE_CURRENCY", length = 3)
    })
    private MonetaryAmount buyNowPrice;

    private Dimensions dimensions;

    private Weight weight;

    /*
        Hibernate will call <code>getName()</code> and <code>setName()</code> when loading and storing items.
    */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = !name.startsWith("AUCTION: ") ? "AUCTION: " + name : name;
    }

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public String getDescription() {
        return description.toString();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuctionType getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {
        this.auctionType = auctionType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public BigDecimal getAverageBidAmount() {
        return averageBidAmount;
    }

    public double getMetricWeight() {
        return metricWeight;
    }

    public void setMetricWeight(double metricWeight) {
        this.metricWeight = metricWeight;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public MonetaryAmount getInitialPrice() {
        return initialPrice;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public void addBid(Bid bid) {
        if (bid == null)
            throw new NullPointerException("Can't add null Bid");
        if (bid.getItem() != null)
            throw new IllegalStateException("Bid is already assigned to an Item");
        bids.add(bid);
        bid.setItem(this);
    }

    public void setInitialPrice(MonetaryAmount initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public MonetaryAmount getBuyNowPrice() {
        return buyNowPrice;
    }

    public void setBuyNowPrice(MonetaryAmount buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }
}
