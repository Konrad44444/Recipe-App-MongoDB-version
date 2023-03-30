package com.mongo.recipeapp.recipeappmongo.commands;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {
    private String id;
    private String recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;

}
