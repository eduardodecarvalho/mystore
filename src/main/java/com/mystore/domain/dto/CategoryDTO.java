package com.mystore.domain.dto;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = -6611242501590194784L;

    private Integer id;
    private String name;

    private List<ProductDTO> products;

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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(final List<ProductDTO> products) {
        this.products = products;
    }

}
