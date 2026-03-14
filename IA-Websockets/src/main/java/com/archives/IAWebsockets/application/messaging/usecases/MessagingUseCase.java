package com.archives.IAWebsockets.application.messaging.usecases;

import com.archives.IAWebsockets.domain.messaging.ChatMessageResponseEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessagingUseCase {
    Mono<Void> handleMessage(String sessionId, String message);

    Flux<ChatMessageResponseEvent> handleResponse(String sessionId);
}
