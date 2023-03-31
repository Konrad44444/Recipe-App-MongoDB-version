package com.mongo.recipeapp.recipeappmongo.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mongo.recipeapp.recipeappmongo.model.Recipe;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
    
}
