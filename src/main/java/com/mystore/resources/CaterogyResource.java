package com.mystore.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.domain.dto.CategoryDTO;
import com.mystore.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CaterogyResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> findAll() {
        return categoryService.findAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable final Integer id) {
        return new CategoryDTO(categoryService.findById(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody final CategoryDTO categoryDTO) {
        return categoryService.create(categoryDTO.getCategory());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id) {
        categoryService.delete(id);
    }
}
