package com.mystore.domain.dto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mystore.domain.Product;

public class ProductDTO {

    private Product product;

    public ProductDTO() {
        product = new Product();
    }

    public ProductDTO(final Product product) {
        this.product = product;
    }

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public Integer getId() {
        return product.getId();
    }

    public void setId(final Integer id) {
        product.setId(id);
    }

    public String getSku() {
        return product.getSku();
    }

    public void setSku(final String sku) {
        product.setSku(sku);
    }

    public String getName() {
        return product.getName();
    }

    public void setName(final String name) {
        product.setName(name);
    }

    public String getDescription() {
        return product.getDescription();
    }

    public void setDescription(final String description) {
        product.setDescription(description);
    }

    public BigDecimal getPrice() {
        return product.getPrice();
    }

    public void setPrice(final BigDecimal price) {
        product.setPrice(price);
    }

    public Long getQuantity() {
        return product.getQuantity();
    }

    public void setQuantity(final Long quantity) {
        product.setQuantity(quantity);
    }

    public List<CategoryDTO> getCategories() {
        if (product.getCategories() == null) {
            return Collections.emptyList();
        }
        return product.getCategories().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public void setCategories(final List<CategoryDTO> categories) {
        product.setCategories(categories.stream().map(CategoryDTO::getCategory).collect(Collectors.toList()));
    }

    public String getModel() {
        return product.getModel();
    }

    public void setModel(final String model) {
        product.setModel(model);
    }
}
