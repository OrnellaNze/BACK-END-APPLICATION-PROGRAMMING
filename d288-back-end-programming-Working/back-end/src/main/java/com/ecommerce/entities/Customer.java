package com.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter


public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    //@JsonProperty("id")
    private Long id;

    @Column(name = "customer_first_name")
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "customer_last_name")
    @JsonProperty("lastName")
    private String lastName;

    @Column(name = "address")
    @JsonProperty("address")
    private String address;

    @Column(name = "postal_code")
    @JsonProperty("postal_code")
    private String postal_code;

    @Column(name = "phone")
    @JsonProperty("phone")
    private String phone;

    @Column(name = "create_date")
    @JsonProperty("createDate")
    @CreationTimestamp
    private Date create_date;
    @Column(name = "last_update")
    @JsonProperty("lastUpdate")
    @UpdateTimestamp

    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "division_id", referencedColumnName = "division_id")
    private Division division;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private Set<Cart> carts = new HashSet<>();


    public Customer() {
    }

    public Customer(long id, String firstName, String lastName, String address, String postal_code, String phone, Date create_date, Date last_update, Division division, Set<Cart> carts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.create_date = create_date;
        this.last_update = last_update;
        this.division = division;
        this.carts = carts;
    }



    public void add(Cart cart) {
        if (cart != null) {
            if (carts == null) {
                carts = new HashSet<>();
            }
            carts.add(cart);
            cart.setCustomer(this);
        }



    }
}
