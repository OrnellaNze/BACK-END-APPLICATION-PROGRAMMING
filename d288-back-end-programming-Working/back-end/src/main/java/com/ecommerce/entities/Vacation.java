package com.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "vacations")
@Getter
@Setter
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "vacation_id")
    @JsonProperty("id")
    long id;
    @Column(name = "vacation_title")
    @JsonProperty("vacation_title")
    String vacation_title;
    @Column(name = "description")
    @JsonProperty("description")
    String description;
    @Column(name = "travel_fare_price")
    @JsonProperty("travel_price")
    BigDecimal travel_price;
    @Column(name = "image_url")
    @JsonProperty("image_URL")
    String image_URL;
    @Column(name = "create_date")
    @CreationTimestamp
    @JsonProperty("create_date")
    Date create_date;
    @Column(name = "last_update")
    @UpdateTimestamp
    @JsonProperty("last_update")
    Date last_update;
    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL)
    Set<Excursion> excursions;

    public Vacation() {

    }

}