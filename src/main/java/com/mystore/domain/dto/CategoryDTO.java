package com.mystore.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mystore.domain.Category;

public class CategoryDTO {

    private Category category;

    public CategoryDTO() {
        category = new Category();
    }

    public CategoryDTO(final Category category) {
        this.category = category;
    }

    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    public Integer getId() {
        return category.getId();
    }

    public void setId(final Integer id) {
        category.setId(id);
    }

    public String getName() {
        return category.getName();
    }

    public void setName(final String name) {
        category.setName(name.trim());
    }

}
