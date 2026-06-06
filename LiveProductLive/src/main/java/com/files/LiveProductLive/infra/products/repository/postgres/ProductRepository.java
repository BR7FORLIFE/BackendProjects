package com.files.LiveProductLive.infra.products.repository.postgres;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.files.LiveProductLive.infra.products.entity.ProductEntity;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, UUID> {

}
