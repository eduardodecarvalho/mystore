package com.mystore.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoriesId(Integer id);

    Optional<Product> findByName(String name);

    Optional<Product> findByModel(String name);

}
