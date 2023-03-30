package com.mongo.recipeapp.recipeappmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mongo.recipeapp.recipeappmongo.controllers.DatabaseController;


@SpringBootApplication
public class RecipeAppMongoApplication {
	

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(RecipeAppMongoApplication.class, args);

		System.out.println("----- Cleaning database -----");
		DatabaseController databaseController = (DatabaseController) ctx.getBean("databaseController");
		//databaseController.clearDatabase();
	}

}
