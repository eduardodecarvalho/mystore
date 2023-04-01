package com.mystore.resources;

import com.mystore.domain.dto.ProductDTO;
import com.mystore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> findAll() {
        return productService.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable final Integer id) {
        return new ProductDTO(productService.findById(id));
    }

    @GetMapping("categories/{categoryId}")
    public List<ProductDTO> findByCategory(@PathVariable final Integer categoryId) {
        return productService.findByCategory(categoryId).stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody final ProductDTO productDTO) {
        return productService.create(productDTO.getProduct());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id) {
        productService.delete(id);
    }
}
