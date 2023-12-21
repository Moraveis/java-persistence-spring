package com.study.javapersistence.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Embeddable
@AttributeOverride(name = "name", column = @Column(name = "WEIGHT_NAME"))
@AttributeOverride(name = "symbol", column = @Column(name = "WEIGHT_SYMBOL"))
public class Weight extends Measurement {

    @NotNull
    @Column(name = "WEIGHT")
    private BigDecimal value;

    public Weight() {
    }

    public Weight(String name, String symbol, BigDecimal weight) {
        super(name, symbol);
        this.value = weight;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s%s", this.value, this.getSymbol());
    }
}
