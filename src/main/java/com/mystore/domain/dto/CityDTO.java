package com.mystore.domain.dto;

import com.mystore.domain.City;

public class CityDTO {

    private City city;

    public Integer getId() {
        return city.getId();
    }

    public void setId(final Integer id) {
        city.setId(id);
    }

    public String getName() {
        return city.getName();
    }

    public void setName(final String name) {
        city.setName(name);
    }
}
