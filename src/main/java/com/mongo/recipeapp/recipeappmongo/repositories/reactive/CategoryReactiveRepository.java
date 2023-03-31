package com.mongo.recipeapp.recipeappmongo.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mongo.recipeapp.recipeappmongo.model.Category;

import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> findFirstByDescription(String description);
}
