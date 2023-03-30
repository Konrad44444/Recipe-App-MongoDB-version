package com.mongo.recipeapp.recipeappmongo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mongo.recipeapp.recipeappmongo.commands.RecipeCommand;
import com.mongo.recipeapp.recipeappmongo.exceptions.NotFoundException;
import com.mongo.recipeapp.recipeappmongo.services.RecipeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class RecipeController {
    private static final String RECIPE = "recipe";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show") //id is variable
    public String showById(@PathVariable String id, Model model) { 
            model.addAttribute(RECIPE, recipeService.findById(id));
            
            return "recipe/show";

    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute(RECIPE, new RecipeCommand());

        return "recipe/recipeform";
    }

    //after adding show recipe
    @PostMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute(RECIPE, recipeService.findCommandById(id));

        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        recipeService.deleteById(id);

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class) 
    public ModelAndView handleNotFount(Exception exception) {
        log.error("Handling not found exception");
        log.error(exception.getMessage());
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        

        return modelAndView;
        
    }

}