package com.mystore.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mystore.domain.enums.ClientType;

@Entity
@Table(name = "CLIENT")
public class Client {

    private Integer id;
    private String name;
    private String email;
    private String nationalRegister;
    private ClientType clientType;

    private List<Address> addresses;
    private List<ClientPhone> phones;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "client")
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(final List<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToMany(mappedBy = "client")
    public List<ClientPhone> getPhones() {
        return phones;
    }

    public void setPhones(final List<ClientPhone> phones) {
        this.phones = phones;
    }

}
