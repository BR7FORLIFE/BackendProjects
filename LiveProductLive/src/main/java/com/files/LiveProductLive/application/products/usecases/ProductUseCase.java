package com.files.LiveProductLive.application.products.usecases;

import java.util.List;
import java.util.UUID;

import com.files.LiveProductLive.domain.products.ProductModel;

import reactor.core.publisher.Mono;

public interface ProductUseCase {
    Mono<String> createProduct(String name, Float price);

    Mono<Void> establishViewByProductId(UUID id);

    Mono<List<ProductModel>> getAll();
}
