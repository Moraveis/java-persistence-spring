package com.study.javapersistence.domain;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Data
public class Item {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @NotNull
    @Size(min = 2, max = 255, message = "Name is required, maximum 255 characters.")
    private String name;

    @Future
    private Date auctionEnd;

}
