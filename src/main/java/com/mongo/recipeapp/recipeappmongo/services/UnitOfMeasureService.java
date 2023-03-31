package com.mongo.recipeapp.recipeappmongo.services;

import com.mongo.recipeapp.recipeappmongo.commands.UnitOfMeasureCommand;

import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {
    //flux means there're many of uoms, before was Set
    Flux<UnitOfMeasureCommand> listAllUoms();
}