package com.mongo.recipeapp.recipeappmongo.services;

import org.springframework.stereotype.Service;

import com.mongo.recipeapp.recipeappmongo.commands.UnitOfMeasureCommand;
import com.mongo.recipeapp.recipeappmongo.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.mongo.recipeapp.recipeappmongo.repositories.reactive.UnitOfMeasureReactiveRepository;

import reactor.core.publisher.Flux;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureReactiveRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Flux<UnitOfMeasureCommand> listAllUoms() {
        return unitOfMeasureReactiveRepository.findAll().map(unitOfMeasureToUnitOfMeasureCommand::convert);

        // return StreamSupport.stream(unitOfMeasureRepository.findAll()
        // .spliterator(), false)
        // .map(unitOfMeasureToUnitOfMeasureCommand::convert)
        // .collect(Collectors.toSet());
    }
    
}