package com.mongo.recipeapp.recipeappmongo.services;

import org.springframework.web.multipart.MultipartFile;

import reactor.core.publisher.Mono;

public interface ImageService {
    Mono<Void> saveImageFile(String id, MultipartFile file);
}
