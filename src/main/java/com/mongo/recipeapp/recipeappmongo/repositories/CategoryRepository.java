package com.mongo.recipeapp.recipeappmongo.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mongo.recipeapp.recipeappmongo.model.Category;

public interface CategoryRepository extends CrudRepository<Category, String> {
    Optional<Category> findFirstByDescription(String description);
}
