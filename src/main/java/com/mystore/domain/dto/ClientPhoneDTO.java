package com.mystore.domain.dto;

public class ClientPhoneDTO {

    private Integer id;
    private ClientDTO client;
    private String phone;
    private int order;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(final ClientDTO client) {
        this.client = client;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(final int order) {
        this.order = order;
    }

}
