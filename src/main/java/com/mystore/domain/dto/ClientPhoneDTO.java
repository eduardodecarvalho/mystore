package com.mystore.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mystore.domain.ClientPhone;

public class ClientPhoneDTO {

    private ClientPhone phone;

    public ClientPhoneDTO() {
        phone = new ClientPhone();
    }

    public ClientPhoneDTO(final ClientPhone phone) {
        this.phone = phone;
    }

    @JsonIgnore
    public ClientPhone getClientPhone() {
        return phone;
    }

    public Integer getId() {
        return phone.getId();
    }

    public void setId(final Integer id) {
        phone.setId(id);
    }

    public String getPhoneNumber() {
        return phone.getPhoneNumber();
    }

    public void setPhoneNumber(final String phoneNumber) {
        phone.setPhoneNumber(phoneNumber);
    }

    public int getPhoneOrder() {
        return phone.getPhoneOrder();
    }

    public void setPhoneOrder(final int phoneOrder) {
        phone.setPhoneOrder(phoneOrder);
    }
}
