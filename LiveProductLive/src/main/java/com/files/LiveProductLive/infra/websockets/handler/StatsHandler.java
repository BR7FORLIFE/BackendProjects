package com.files.LiveProductLive.infra.websockets.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StatsHandler implements WebSocketHandler {

    // webSocketSession es el resultado del upgrade, en el sentido de que cuando se
    // hace un switching de http -> ws el resultado de dicha operacion en spring se
    // llama webSocketSession esto nos permite acceder a informacion de dicha
    // actualizacion de protocolos ademas de enviar y recibir informacion por el
    // canal

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(
                Flux.just(
                        session.textMessage("hello from stats handler")))
                .doOnNext(result -> System.out.println(session.getHandshakeInfo()));
    }
}
