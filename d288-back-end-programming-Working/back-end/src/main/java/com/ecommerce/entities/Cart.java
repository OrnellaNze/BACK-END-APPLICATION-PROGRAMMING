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
@Table(name = "carts")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    long id;

    @Column(name = "package_price")
    @JsonProperty("package_price")
    private BigDecimal package_price;
    @Column(name = "party_size")
    @JsonProperty("party_size")
    private int party_size;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JsonProperty("status")
    private StatusType statusType;
    @Column(name = "order_tracking_number")
    @JsonProperty("orderTrackingNumber")
    private String orderTrackingNumber;

    @Column(name = "create_date")
    @JsonProperty("create_date")
    @CreationTimestamp
    private Date create_date;
    @Column(name = "last_update")
    @UpdateTimestamp
    @JsonProperty("last_update")
    private Date last_update;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "cart", fetch = FetchType.LAZY)
    private Set<CartItem> cartItems = new HashSet<>();

    public void add(CartItem item) {
        if (item != null) {
            if (cartItems == null) {
            }
            cartItems.add(item);
            item.setCart(this);
        }
    }
    public Cart() {

    }
}
