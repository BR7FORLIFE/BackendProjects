package com.archives.IAWebsockets.application.IAchat.usecases;

import com.archives.IAWebsockets.domain.messaging.ChatMessageResponseEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAChatUseCase {
    Mono<Void> processMessage(ChatMessageResponseEvent event);

    Flux<Void> startedConsumed();
}
