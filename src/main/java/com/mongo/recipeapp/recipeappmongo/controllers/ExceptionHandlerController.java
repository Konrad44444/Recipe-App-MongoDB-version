package com.mongo.recipeapp.recipeappmongo.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {
    
    //global exception handler
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(NumberFormatException.class)
    // public ModelAndView handleNumberFormat(Exception exception) {
    //     log.error("Handling bad request");
    //     log.error(exception.getMessage());

    //     ModelAndView modelAndView = new ModelAndView();
    //     modelAndView.setViewName("error400");

    //     String message = exception.getMessage();

    //     modelAndView.addObject("exception", message);

    //     return modelAndView;
    // }
    
}
