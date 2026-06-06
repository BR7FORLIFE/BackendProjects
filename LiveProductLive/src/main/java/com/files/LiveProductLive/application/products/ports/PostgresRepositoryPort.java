package com.files.LiveProductLive.application.products.ports;

import com.files.LiveProductLive.domain.products.ProductModel;

import reactor.core.publisher.Mono;

public interface PostgresRepositoryPort {
    Mono<ProductModel> save(ProductModel productModel);
    
}
