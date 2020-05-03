package com.mystore.domain.dto;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mystore.domain.Client;
import com.mystore.domain.enums.ClientType;

public class ClientDTO {

    private Client client;

    public ClientDTO() {
        client = new Client();
    }

    public ClientDTO(final Client client) {
        this.client = client;
    }

    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public Integer getId() {
        return client.getId();
    }

    public void setId(final Integer id) {
        client.setId(id);
    }

    public String getName() {
        return client.getName();
    }

    public void setName(final String name) {
        client.setName(name);
    }

    public String getEmail() {
        return client.getEmail();
    }

    public void setEmail(final String email) {
        client.setEmail(email);
    }

    public String getNationalRegister() {
        return client.getNationalRegister();
    }

    public void setNationalRegister(final String nationalRegister) {
        client.setNationalRegister(nationalRegister);
    }

    public ClientType getClientType() {
        return client.getClientType();
    }

    public void setClientType(final ClientType clientType) {
        client.setClientType(clientType);
    }

    public List<AddressDTO> getAddresses() {
        if (client.getAddresses() == null) {
            return Collections.emptyList();
        }
        return client.getAddresses().stream().map(AddressDTO::new).collect(Collectors.toList());
    }

    public void setAddresses(final List<AddressDTO> addressesDTO) {
        client.setAddresses(addressesDTO.stream().map(AddressDTO::getAddress).collect(Collectors.toList()));
    }

    public Set<ClientPhoneDTO> getPhones() {
        if (client.getPhones() == null) {
            return Collections.emptySet();
        }
        return client.getPhones().stream().map(ClientPhoneDTO::new).collect(Collectors.toSet());
    }

    public void setPhones(final Set<ClientPhoneDTO> phones) {
        client.setPhones(phones.stream().map(ClientPhoneDTO::getClientPhone).collect(Collectors.toSet()));
    }
}
