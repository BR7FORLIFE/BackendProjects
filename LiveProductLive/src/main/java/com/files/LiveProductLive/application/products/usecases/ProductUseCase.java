package com.files.LiveProductLive.application.products.usecases;

import java.util.List;

import com.files.LiveProductLive.domain.products.ProductModel;

import reactor.core.publisher.Mono;

public interface ProductUseCase {
    Mono<String> createProduct(String name, Float price, String url);

    Mono<List<ProductModel>> getAll();
}
