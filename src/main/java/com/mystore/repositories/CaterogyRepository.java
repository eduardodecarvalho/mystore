package com.mystore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.domain.Category;

public interface CaterogyRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);

}
