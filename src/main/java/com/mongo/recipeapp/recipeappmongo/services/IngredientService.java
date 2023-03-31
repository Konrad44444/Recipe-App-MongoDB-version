package com.mongo.recipeapp.recipeappmongo.services;

import com.mongo.recipeapp.recipeappmongo.commands.IngredientCommand;

import reactor.core.publisher.Mono;

public interface IngredientService {

    //Mono means that there is only one
    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);
    Mono<Void> deleteIngredientById(String recipeId, String id);
}