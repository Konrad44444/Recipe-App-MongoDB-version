package com.mongo.recipeapp.recipeappmongo.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mongo.recipeapp.recipeappmongo.model.Recipe;
import com.mongo.recipeapp.recipeappmongo.repositories.reactive.RecipeReactiveRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ImageServiceImpl  implements ImageService{

    private final RecipeReactiveRepository recipeReactiveRepository;


    public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }


    @Override
    @Transactional
    public Mono<Void> saveImageFile(String id, MultipartFile file) {
        log.debug("Received a file");
        
        try {
            Recipe recipe = recipeReactiveRepository.findById(id).block();
            
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for(byte b : file.getBytes()) byteObjects[i++] = b;
            
            recipe.setImage(byteObjects);

            recipeReactiveRepository.save(recipe).block();
        } catch (IOException e) {
            log.error("Error occured", e);
            e.printStackTrace();
        }
        
        return Mono.empty();
    }
    
}
