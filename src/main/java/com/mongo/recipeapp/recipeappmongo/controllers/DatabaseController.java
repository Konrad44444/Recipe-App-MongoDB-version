package com.mongo.recipeapp.recipeappmongo.controllers;

import org.springframework.stereotype.Controller;

import com.mongo.recipeapp.recipeappmongo.repositories.CategoryRepository;
import com.mongo.recipeapp.recipeappmongo.repositories.RecipeRepository;
import com.mongo.recipeapp.recipeappmongo.repositories.UnitOfMeasureRepository;

@Controller
public class DatabaseController {
    private final RecipeRepository recipeRepository;
	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;


    public DatabaseController(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public void clearDatabase() {
        recipeRepository.deleteAll();
        categoryRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();
    }
}
