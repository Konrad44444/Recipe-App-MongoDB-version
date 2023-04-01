package com.mongo.recipeapp.recipeappmongo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongo.recipeapp.recipeappmongo.commands.RecipeCommand;
import com.mongo.recipeapp.recipeappmongo.converters.RecipeCommandToRecipe;
import com.mongo.recipeapp.recipeappmongo.converters.RecipeToRecipeCommand;
import com.mongo.recipeapp.recipeappmongo.exceptions.NotFoundException;
import com.mongo.recipeapp.recipeappmongo.model.Recipe;
import com.mongo.recipeapp.recipeappmongo.repositories.reactive.RecipeReactiveRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j //logger
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeReactiveRepository recipeReactiveRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;


    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }
    
    @Override
    public Flux<Recipe> getRecipes() {
        log.debug("Recipe Service");

        // List<Recipe> recipes = new ArrayList<>();
        // recipeReactiveRepository.findAll().iterator().forEachRemaining(recipes::add);
        
        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String string) {
        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(string);

        if(recipeMono.block() == null) {
            throw new NotFoundException("Recipe not found! Recipe ID is: " + string.toString());
        }
    
        return recipeMono;
    }

    @Override
    @Transactional
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeReactiveRepository.save(detachedRecipe).block();
        log.debug("Saved RecipeID: " + savedRecipe.getId());

        return Mono.just(recipeToRecipeCommand.convert(savedRecipe));
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(findById(id).block());

        //enhance command object with id value
        if(recipeCommand.getIngredients() != null && !recipeCommand.getIngredients().isEmpty()){
            recipeCommand.getIngredients().forEach(rc ->
                rc.setRecipeId(recipeCommand.getId())
            );
        }

        return Mono.just(recipeCommand);
    }

    @Override
    public Mono<Void> deleteById(String idToDelete) {
        recipeReactiveRepository.deleteById(idToDelete);   
        
        return Mono.empty();
    }
     
}

