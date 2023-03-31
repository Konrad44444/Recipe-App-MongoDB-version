package com.mongo.recipeapp.recipeappmongo.repositories.reactive;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.mongo.recipeapp.recipeappmongo.model.Recipe;    
    
@DataMongoTest
public class RecipeReactiveRepositoryTest {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @BeforeEach
    public void setup(){
        recipeReactiveRepository.deleteAll().block();
    }
        
    @Test
    public void test() {
        Recipe recipe = new Recipe();
        recipe.setDescription("mmmm");

        recipeReactiveRepository.save(recipe).block();

        assertEquals(1L, recipeReactiveRepository.count().block());
    }
}
    