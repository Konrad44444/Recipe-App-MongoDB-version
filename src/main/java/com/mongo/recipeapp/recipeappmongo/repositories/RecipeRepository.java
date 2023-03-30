package com.mongo.recipeapp.recipeappmongo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mongo.recipeapp.recipeappmongo.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
    
}
