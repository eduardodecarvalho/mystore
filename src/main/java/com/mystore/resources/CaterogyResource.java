package com.mystore.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import com.mystore.domain.Category;
import com.mystore.domain.dto.CategoryNameDTO;
import com.mystore.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CaterogyResource {

    @Autowired
    private CategoryService categoryService;

    final ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<CategoryNameDTO> findAll() {
        return categoryService.findAll().stream().map(c -> modelMapper.map(c, CategoryNameDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryNameDTO findById(@PathVariable final Integer id) {
        return modelMapper.map(categoryService.findById(id), CategoryNameDTO.class);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody final CategoryNameDTO categoryDTO) {
        return categoryService.create(modelMapper.map(categoryDTO, Category.class));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id) {
        categoryService.delete(id);
    }
}
