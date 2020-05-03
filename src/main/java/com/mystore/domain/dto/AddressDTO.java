package com.mystore.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mystore.domain.Address;
import com.mystore.domain.City;
import com.mystore.domain.Client;

public class AddressDTO {

    private Address address;

    public AddressDTO(Address address) {
        this.address = address;
    }

    @JsonIgnore
    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Integer getId() {
        return address.getId();
    }

    public void setId(final Integer id) {
        address.setId(id);
    }

    public String getStreet() {
        return address.getStreet();
    }

    public void setStreet(final String street) {
        address.setStreet(street);
    }

    public String getNumber() {
        return address.getNumber();
    }

    public void setNumber(final String number) {
        address.setNumber(number);
    }

    public String getNeighborhood() {
        return address.getNeighborhood();
    }

    public void setNeighborhood(final String neighborhood) {
        address.setNeighborhood(neighborhood);
    }

    public String getZipCode() {
        return address.getZipCode();
    }

    public void setZipCode(final String zipCode) {
        address.setZipCode(zipCode);
    }

    public String getComplement() {
        return address.getComplement();
    }

    public void setComplement(final String complement) {
        address.setComplement(complement);
    }

    public Client getClient() {
        return address.getClient();
    }

    public void setClient(final Client client) {
        address.setClient(client);
    }

    public City getCity() {
        return address.getCity();
    }

    public void setCity(final City city) {
        address.setCity(city);
    }

}
