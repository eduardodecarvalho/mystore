package com.mystore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT")
public class Address {

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
