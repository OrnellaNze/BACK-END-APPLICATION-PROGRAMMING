package com.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "excursions")
@Getter
@Setter
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id")
    @JsonProperty("id")
    private long id;

    @Column(name = "excursion_title")
    @JsonProperty("excursion_title")
    private String excursionTitle;

    @Column(name = "excursion_price")
    @JsonProperty("excursion_price")
    private BigDecimal excursionPrice;

    @Column(name = "image_url")
    @JsonProperty("image_URL")
    private String imageUrl;

    @Column(name = "create_date")
    @CreationTimestamp
    @JsonProperty("create_date")
    private Date createDate;
    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "vacation_id", nullable = false)
    private Vacation vacation;

    @ManyToMany(mappedBy = "excursions", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems = new HashSet<>();

    public Excursion() {
    }

}