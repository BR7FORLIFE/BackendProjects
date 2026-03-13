package com.archives.IAWebsockets.application.messaging.ports;

import com.archives.IAWebsockets.domain.messaging.ChatMessageRequestEvent;

import reactor.core.publisher.Mono;

public interface MessagePublisherPort {
    Mono<Void> publishChatRequest(ChatMessageRequestEvent event);
}
