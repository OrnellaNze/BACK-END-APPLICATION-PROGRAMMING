package com.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "divisions")
@Getter
@Setter
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @JsonProperty("id")
    @Column(name = "division_id")
    private long id;

    @Column(name = "division")
    @JsonProperty("division_name")
    private String divisionName;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "last_update")
    @UpdateTimestamp

    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false)
    @JsonProperty("country")
    private Country country;

    @Column(name = "country_id", insertable = false, updatable = false)
    @JsonProperty("country_id")
    private long countryId;

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL)
    private Set<Customer> customers;
    public Division() {

    }

}
