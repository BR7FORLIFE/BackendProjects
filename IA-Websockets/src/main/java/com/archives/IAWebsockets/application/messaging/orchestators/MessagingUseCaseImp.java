package com.archives.IAWebsockets.application.messaging.orchestators;

import com.archives.IAWebsockets.application.messaging.usecases.MessagingUseCase;

import reactor.core.publisher.Mono;

public class MessagingUseCaseImp implements MessagingUseCase {
    @Override
    public Mono<Void> handleMessage(String message) {
        return null;
    }
}
