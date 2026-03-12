package com.archives.IAWebsockets.infra.websockets.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.archives.IAWebsockets.application.messaging.usecases.MessagingUseCase;

import reactor.core.publisher.Mono;

@Component
public class ChatWebSocketsHandler implements WebSocketHandler {

    private final MessagingUseCase messagingUseCase;

    public ChatWebSocketsHandler(MessagingUseCase messagingUseCase) {
        this.messagingUseCase = messagingUseCase;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .flatMap(msg -> messagingUseCase.handleMessage(msg))
                .then();
    }
}
