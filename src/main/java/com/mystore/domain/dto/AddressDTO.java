package com.mystore.domain.dto;

public class AddressDTO {

    private Integer id;
    private String street;
    private String number;
    private String neighborhood;
    private String zipCode;
    private String complement;

    private ClientDTO client;
    private CityDTO city;

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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(final ClientDTO client) {
        this.client = client;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(final CityDTO city) {
        this.city = city;
    }

}
