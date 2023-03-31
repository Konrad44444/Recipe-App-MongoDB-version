package com.mongo.recipeapp.recipeappmongo.repositories.reactive;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.mongo.recipeapp.recipeappmongo.model.UnitOfMeasure;    
    
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {
    UnitOfMeasure uom = new UnitOfMeasure();

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @BeforeEach
    public void setup(){
        unitOfMeasureReactiveRepository.deleteAll().block();
    }
        
    @Test
    public void test() {
        uom.setId("1");
        uom.setDescription("some uom");

        unitOfMeasureReactiveRepository.save(uom).block();

        assertEquals(1L, unitOfMeasureReactiveRepository.count().block());
    }
}
    