package com.mongo.recipeapp.recipeappmongo.services;

import java.util.Set;

import com.mongo.recipeapp.recipeappmongo.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}