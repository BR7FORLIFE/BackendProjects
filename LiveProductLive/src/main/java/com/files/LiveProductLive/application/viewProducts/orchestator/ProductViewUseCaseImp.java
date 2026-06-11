package com.files.LiveProductLive.application.viewProducts.orchestator;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.files.LiveProductLive.application.viewProducts.publishers.ProductViewProducer;
import com.files.LiveProductLive.application.viewProducts.usecases.ProductViewUseCase;

import reactor.core.publisher.Mono;

@Service
public class ProductViewUseCaseImp implements ProductViewUseCase {

    private final ProductViewProducer producer;

    public ProductViewUseCaseImp(ProductViewProducer producer) {
        this.producer = producer;
    }

    @Override
    public Mono<Void> setViewInProduct(UUID productId) {
        return producer.publish(productId);
    }
}
