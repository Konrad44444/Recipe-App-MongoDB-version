package com.mongo.recipeapp.recipeappmongo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mongo.recipeapp.recipeappmongo.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, String> {
    Optional<UnitOfMeasure> findFirstByDescription(String description);
}
