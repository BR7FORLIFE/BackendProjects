package com.files.LiveProductLive.infra.products.repository;

import org.springframework.stereotype.Repository;

import com.files.LiveProductLive.application.products.ports.PostgresRepositoryPort;
import com.files.LiveProductLive.domain.products.ProductModel;
import com.files.LiveProductLive.infra.products.entity.ProductEntity;
import com.files.LiveProductLive.infra.products.mapper.ProductMapper;
import com.files.LiveProductLive.infra.products.repository.postgres.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoryAdapter implements PostgresRepositoryPort {

    private final ProductRepository productRepository;

    public ProductRepositoryAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<ProductModel> save(ProductModel productModel) {
        ProductEntity entity = ProductMapper.toEntity(productModel);
        return productRepository.save(entity).map(ProductMapper::toDomain);
    }

    @Override
    public Flux<ProductModel> findAll() {
        return productRepository.findAll().map(ProductMapper::toDomain);
    }
}
