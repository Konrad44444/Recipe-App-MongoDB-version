package com.mongo.recipeapp.recipeappmongo.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mongo.recipeapp.recipeappmongo.commands.UnitOfMeasureCommand;
import com.mongo.recipeapp.recipeappmongo.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.mongo.recipeapp.recipeappmongo.model.UnitOfMeasure;
import com.mongo.recipeapp.recipeappmongo.repositories.reactive.UnitOfMeasureReactiveRepository;

import reactor.core.publisher.Flux;    
    
public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        service = new UnitOfMeasureServiceImpl(unitOfMeasureReactiveRepository, unitOfMeasureCommand);
    }
        
    @Test
    public void test() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1");
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2");

        when(unitOfMeasureReactiveRepository.findAll()).thenReturn(Flux.just(uom1, uom2));

        //when
        List<UnitOfMeasureCommand> commands = service.listAllUoms().collectList().block();

        //then
        assertEquals(2, commands.size());
        verify(unitOfMeasureReactiveRepository, times(1)).findAll();

    }
}
    