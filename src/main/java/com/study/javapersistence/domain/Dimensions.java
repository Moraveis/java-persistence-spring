package com.study.javapersistence.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Embeddable
@AttributeOverride(name = "name", column = @Column(name = "DIMENSIONS_NAME"))
@AttributeOverride(name = "symbol", column = @Column(name = "DIMENSIONS_SYMBOL"))
public class Dimensions extends Measurement {

    @NotNull
    private BigDecimal depth;

    @NotNull
    private BigDecimal height;

    @NotNull
    private BigDecimal width;

    public Dimensions() {
    }

    public Dimensions(String name, String symbol, BigDecimal depth, BigDecimal height, BigDecimal width) {
        super(name, symbol);
        this.depth = depth;
        this.height = height;
        this.width = width;
    }

    public BigDecimal getDepth() {
        return depth;
    }

    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return String.format("W:%s%s x H:%s%s x D:%s%s", this.height, this.getSymbol(), this.width, this.getSymbol(), this.depth, this.getSymbol());
    }
}
