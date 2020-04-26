package com.mystore.domain.dto;

import java.io.Serializable;

public class CategoryNameDTO implements Serializable {

    private static final long serialVersionUID = -3730333380727565343L;

    private Integer id;
    private String name;

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

}
