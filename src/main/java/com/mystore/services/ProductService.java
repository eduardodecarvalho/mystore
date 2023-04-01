package com.mystore.services;

import com.mystore.domain.Product;
import com.mystore.exceptions.MyStoreBusinessException;
import com.mystore.repositories.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(final Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new MyStoreBusinessException(MyStoreBusinessException.PRODUCT_NOT_FOUND));
    }

    public List<Product> findByCategory(final Integer categoryId) {
        return productRepository.findByCategoriesId(categoryId);
    }

    public Integer create(final Product product) {
        if (StringUtils.isBlank(product.getName())) {
            throw new MyStoreBusinessException(MyStoreBusinessException.PRODUCT_SHOULD_HAVE_A_NAME);
        }
        if (productRepository.existsByName(product.getName())) {
            throw new MyStoreBusinessException(MyStoreBusinessException.PRODUCT_NAME_ALREDY_REGISTERED);
        }
        if (productRepository.findByModel(product.getName()).isPresent()) {
            throw new MyStoreBusinessException(MyStoreBusinessException.PRODUCT_MODEL_ALREDY_REGISTERED);
        }
        return productRepository.save(product).getId();
    }

    public void delete(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new MyStoreBusinessException(MyStoreBusinessException.PRODUCT_HAVE_QUANTITY));
        productRepository.delete(product);
    }

}
