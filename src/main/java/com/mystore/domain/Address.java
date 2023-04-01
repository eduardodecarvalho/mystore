package com.mystore.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

    private static final long serialVersionUID = 1918353387779666377L;

    private Integer id;
    private String street;
    private String number;
    private String neighborhood;
    private String zipCode;
    private String complement;

    private Client client;
    private City city;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(final String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(final String complement) {
        this.complement = complement;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_client")
    public Client getClient() {
        return client;
    }

    public void setClient(final Client client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "id_city")
    public City getCity() {
        return city;
    }

    public void setCity(final City city) {
        this.city = city;
    }

}
