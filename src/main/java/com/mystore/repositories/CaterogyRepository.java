package com.mystore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.domain.Category;

public interface CaterogyRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

}
