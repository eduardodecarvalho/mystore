package com.mystore.domain.dto;

import java.util.List;

import com.mystore.domain.enums.ClientType;

public class ClientDTO {

    private Integer id;
    private String name;
    private String email;
    private String nationalRegister;
    private ClientType clientType;

    private List<AddressDTO> addresses;
    private List<ClientPhoneDTO> phones;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getNationalRegister() {
        return nationalRegister;
    }

    public void setNationalRegister(final String nationalRegister) {
        this.nationalRegister = nationalRegister;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(final ClientType clientType) {
        this.clientType = clientType;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(final List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public List<ClientPhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(final List<ClientPhoneDTO> phones) {
        this.phones = phones;
    }

}
