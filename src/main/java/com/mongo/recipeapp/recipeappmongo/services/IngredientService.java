package com.mongo.recipeapp.recipeappmongo.services;

import com.mongo.recipeapp.recipeappmongo.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteIngredientById(String recipeId, String id);
}