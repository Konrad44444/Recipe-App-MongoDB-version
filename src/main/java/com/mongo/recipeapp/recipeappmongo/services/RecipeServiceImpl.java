package com.mongo.recipeapp.recipeappmongo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongo.recipeapp.recipeappmongo.commands.RecipeCommand;
import com.mongo.recipeapp.recipeappmongo.converters.RecipeCommandToRecipe;
import com.mongo.recipeapp.recipeappmongo.converters.RecipeToRecipeCommand;
import com.mongo.recipeapp.recipeappmongo.exceptions.NotFoundException;
import com.mongo.recipeapp.recipeappmongo.model.Recipe;
import com.mongo.recipeapp.recipeappmongo.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j //logger
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;


    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }
    
    @Override
    public List<Recipe> getRecipes() {
        log.debug("Recipe Service");

        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        
        return recipes;
    }

    @Override
    public Recipe findById(String string) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(string);

        if(!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe not found! Recipe ID is: " + string.toString());
        }
    
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeID: " + savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public RecipeCommand findCommandById(String id) {
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(findById(id));

        //enhance command object with id value
        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients().forEach(rc -> {
                rc.setRecipeId(recipeCommand.getId());
            });
        }

        return recipeCommand;
    }

    @Override
    public void deleteById(String idToDelete) {
        recipeRepository.deleteById(idToDelete);        
    }
     
}

