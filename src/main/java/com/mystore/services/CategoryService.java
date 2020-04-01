package com.mystore.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.domain.Category;
import com.mystore.exceptions.MyStoreBusinessException;
import com.mystore.repositories.CaterogyRepository;

@Service
public class CategoryService {

    @Autowired
    private CaterogyRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(final Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new MyStoreBusinessException(MyStoreBusinessException.CATEGORY_NOT_FOUND));
    }

    public Integer create(final Category category) {
        if (StringUtils.isBlank(category.getName())) {
            throw new MyStoreBusinessException(MyStoreBusinessException.CATEGORY_NAME_SHOULD_HAVE_A_NAME);
        }
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new MyStoreBusinessException(MyStoreBusinessException.CATEGORY_NAME_ALREDY_REGISTERED);
        }
        return categoryRepository.save(category).getId();
    }

}
