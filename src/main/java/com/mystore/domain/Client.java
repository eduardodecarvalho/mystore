package com.mystore.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mystore.domain.enums.ClientType;

@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {

    private static final long serialVersionUID = 7933842952292858061L;

    private Integer id;
    private String name;
    private String email;
    private String nationalRegister;
    private ClientType clientType;

    private List<Address> addresses;
    private Set<ClientPhone> phones;

    public Client() {
    }

    public Client(final Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.nationalRegister = client.getNationalRegister();
        this.clientType = client.getClientType();
        this.addresses = client.getAddresses();
        this.phones = client.getPhones();
    }

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

    @JsonManagedReference
    @OneToMany(mappedBy = "client")
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(final List<Address> addresses) {
        this.addresses = addresses;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "client")
    public Set<ClientPhone> getPhones() {
        return phones;
    }

    public void setPhones(final Set<ClientPhone> phones) {
        this.phones = phones;
    }

}
