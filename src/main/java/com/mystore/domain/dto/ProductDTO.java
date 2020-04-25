package com.mystore.domain.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {

    private Integer id;
    private String sku;
    private String name;
    private String description;
    private String model;
    private BigDecimal price;
    private Long quantity;

    private List<CategoryDTO> categories;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(final String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(final List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

}
