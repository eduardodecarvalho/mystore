package com.mystore.services;

import com.mystore.domain.Category;
import com.mystore.exceptions.MyStoreBusinessException;
import com.mystore.repositories.CaterogyRepository;
import com.mystore.repositories.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CaterogyRepository categoryRepository;

    private final ProductRepository productRepository;

    public CategoryService(CaterogyRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(final Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new MyStoreBusinessException(MyStoreBusinessException.CATEGORY_NOT_FOUND));
    }

    public Integer create(final Category category) {
        if (StringUtils.isBlank(category.getName())) {
            throw new MyStoreBusinessException(MyStoreBusinessException.CATEGORY_SHOULD_HAVE_A_NAME);
        }
        if (categoryRepository.existsByName(category.getName())) {
            throw new MyStoreBusinessException(MyStoreBusinessException.CATEGORY_NAME_ALREDY_REGISTERED);
        }
        return categoryRepository.save(category).getId();
    }

    public void delete(final Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new MyStoreBusinessException(MyStoreBusinessException.CATEGORY_NOT_FOUND));
        if (productRepository.existsByCategoriesId(id)) {
            throw new MyStoreBusinessException(MyStoreBusinessException.CATEGORY_WITH_PRODUCTS);
        }
        categoryRepository.delete(category);
    }

}
