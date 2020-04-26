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

import com.mystore.domain.Product;
import com.mystore.domain.dto.ProductDTO;
import com.mystore.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    final ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<ProductDTO> findAll() {
        return productService.findAll().stream().map(c -> modelMapper.map(c, ProductDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable final Integer id) {
        return modelMapper.map(productService.findById(id), ProductDTO.class);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody final ProductDTO productDTO) {
        return productService.create(modelMapper.map(productDTO, Product.class));
    }

    @GetMapping("categories/{categoryId}")
    public List<ProductDTO> findByCategory(@PathVariable final Integer categoryId) {
        return productService.findByCategory(categoryId).stream().map(c -> modelMapper.map(c, ProductDTO.class)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id) {
        productService.delete(id);
    }
}
