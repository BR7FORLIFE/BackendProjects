package com.archives.IAWebsockets.application.messaging.usecases;

import reactor.core.publisher.Mono;

public interface MessagingUseCase {
    Mono<Void> handleMessage(String sessionId, String message);
}
