package com.mongo.recipeapp.recipeappmongo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mongo.recipeapp.recipeappmongo.commands.IngredientCommand;
import com.mongo.recipeapp.recipeappmongo.commands.RecipeCommand;
import com.mongo.recipeapp.recipeappmongo.commands.UnitOfMeasureCommand;
import com.mongo.recipeapp.recipeappmongo.services.IngredientService;
import com.mongo.recipeapp.recipeappmongo.services.RecipeService;
import com.mongo.recipeapp.recipeappmongo.services.UnitOfMeasureService;


@Controller
public class IngredientController {
    
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model) {
        //use command object to avoid lazy load errors in thymeleaf
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));

        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));

        model.addAttribute("uom_list", unitOfMeasureService.listAllUoms());

        return "/recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command, @PathVariable String recipeId) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command).block();

        return "redirect:/recipe/" + Long.valueOf(recipeId) + "/ingredient/" + savedCommand.getId() + "/show";
    }
    
    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId, Model model) {
        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId).block();

        if(recipeCommand == null) throw new RuntimeException("Incorrect recipe id value!");

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();

        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uom_list", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String delteIngredient(@PathVariable String recipeId, @PathVariable String id) {
        ingredientService.deleteIngredientById(recipeId, id);

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
