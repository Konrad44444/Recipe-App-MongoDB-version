package com.mongo.recipeapp.recipeappmongo.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mongo.recipeapp.recipeappmongo.model.Recipe;
import com.mongo.recipeapp.recipeappmongo.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl  implements ImageService{

    private final RecipeRepository recipeRepository;


    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(String id, MultipartFile file) {
        log.debug("Received a file");
        
        try {
            Recipe recipe = recipeRepository.findById(id).get();
            
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for(byte b : file.getBytes()) byteObjects[i++] = b;
            
            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Error occured", e);
            e.printStackTrace();
        }
        
    }
    
}
