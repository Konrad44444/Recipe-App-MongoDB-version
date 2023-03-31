package com.mongo.recipeapp.recipeappmongo.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mongo.recipeapp.recipeappmongo.model.UnitOfMeasure;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String>{
    
}
