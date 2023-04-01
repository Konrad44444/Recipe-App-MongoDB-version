package com.mongo.recipeapp.recipeappmongo.services;

import com.mongo.recipeapp.recipeappmongo.commands.RecipeCommand;
import com.mongo.recipeapp.recipeappmongo.model.Recipe;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {
    Flux<Recipe> getRecipes();
    Mono<Recipe> findById(String id);
    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);
    Mono<RecipeCommand> findCommandById(String id);
    Mono<Void> deleteById(String id);
}
