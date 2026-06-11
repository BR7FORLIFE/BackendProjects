package com.files.LiveProductLive.application.products.orchestator;

import java.util.List;

import org.springframework.stereotype.Service;

import com.files.LiveProductLive.application.products.ports.PostgresRepositoryPort;
import com.files.LiveProductLive.application.products.ports.RedisRepositoryPort;
import com.files.LiveProductLive.application.products.usecases.ProductUseCase;
import com.files.LiveProductLive.domain.products.ProductModel;

import reactor.core.publisher.Mono;

@Service
public class ProductUseCaseImp implements ProductUseCase {

    private final RedisRepositoryPort redisRepositoryPort;
    private final PostgresRepositoryPort postgresRepositoryPort;

    public ProductUseCaseImp(RedisRepositoryPort redisRepositoryPort, PostgresRepositoryPort postgresRepositoryPort) {
        this.redisRepositoryPort = redisRepositoryPort;
        this.postgresRepositoryPort = postgresRepositoryPort;
    }

    @Override
    public Mono<String> createProduct(String name, Float price, String url) {
        // creamos el producto tanto postgres como en redis
        ProductModel newProduct = ProductModel.createDraft(name, price, url);

        // postgres -> para obtener los productos
        // redis para poder aumentar inicializar los contadores de vistas
        return Mono.when(
                postgresRepositoryPort.save(newProduct),
                redisRepositoryPort.save(newProduct)).thenReturn("product saved!");
    }

    @Override
    public Mono<List<ProductModel>> getAll() {
        return postgresRepositoryPort.findAll().collectList();
    }
}
