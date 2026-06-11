package com.files.LiveProductLive.application.viewProducts.publishers;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.files.LiveProductLive.application.viewProducts.handlers.ProductViewHandler;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;

@Component
public class ProductViewCustomer {
    private final KafkaReceiver<String, String> receiver;
    private final List<ProductViewHandler> handlers;

    public ProductViewCustomer(KafkaReceiver<String, String> receiver,
            List<ProductViewHandler> handlers) {
        this.receiver = receiver;
        this.handlers = handlers;
    }

    // este metodo evita crear un customer para cada servicio que quiera consumir el
    // topic solo implementando un interfaz que se manejara como handler
    @PostConstruct
    public void init() {
        receiver.receive()
                .flatMap(record -> Flux.fromIterable(handlers)
                        .flatMap(handle -> handle.handle(UUID.fromString(record.value()))
                                .then(
                                        Mono.fromRunnable(record.receiverOffset()::acknowledge))))
                .subscribe();
    }
}
