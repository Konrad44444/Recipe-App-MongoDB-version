package com.mongo.recipeapp.recipeappmongo.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient {

    private String id = UUID.randomUUID().toString();

    private String description;
    private BigDecimal amount;

    private UnitOfMeasure uom;
    
    public Ingredient() {
    }
    
    public Ingredient(String desc, BigDecimal amount, UnitOfMeasure uom) {
        this.description = desc;
        this.amount = amount;
        this.uom = uom;
    }
}
