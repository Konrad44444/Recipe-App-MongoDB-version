package com.mongo.recipeapp.recipeappmongo.services;

import java.util.List;

import com.mongo.recipeapp.recipeappmongo.commands.RecipeCommand;
import com.mongo.recipeapp.recipeappmongo.model.Recipe;

public interface RecipeService {
    List<Recipe> getRecipes();
    Recipe findById(String id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(String id);
    void deleteById(String id);
}
