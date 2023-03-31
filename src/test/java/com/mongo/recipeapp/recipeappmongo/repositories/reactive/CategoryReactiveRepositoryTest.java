package com.mongo.recipeapp.recipeappmongo.repositories.reactive;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.mongo.recipeapp.recipeappmongo.model.Category;

import reactor.core.publisher.Mono;    
    
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @BeforeEach
    public void setup(){
        categoryReactiveRepository.deleteAll().block();
    }
        
    @Test
    public void testSave() {
        Category category = new Category();
        category.setDescription("delicious");

        categoryReactiveRepository.save(category).block();

        assertEquals(1L, categoryReactiveRepository.count().block());
    }

    @Test
    public void testFindByDescription() {
        String desc = "delicious";
        Category category = new Category();
        category.setDescription(desc);

        categoryReactiveRepository.save(category).block();
        
        Category catFromDB = categoryReactiveRepository.findFirstByDescription(desc).block();
       
        //Mono<Category> catFromDB = categoryReactiveRepository.findByDescription(desc);
        //if block() used then it gets object from Mono<Category>
        //assertNotNull(catFromDB.block().getId());

        assertNotNull(catFromDB.getId());
        
    }
}
    