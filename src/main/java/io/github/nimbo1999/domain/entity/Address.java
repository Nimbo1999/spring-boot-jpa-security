package io.github.nimbo1999.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "public_place")
    private String publicPlace;

    @Column
    private String neighborhood;

    @Column
    private String city;

    @Column(name = "federative_unit")
    private String federativeUnit;

    @Column
    private String complement;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Customer customer;
}
