package com.archives.IAWebsockets.application.IAchat.ports;

import com.archives.IAWebsockets.domain.messaging.ChatMessageResponseEvent;

import reactor.core.publisher.Mono;

public interface IAChatPort {
    Mono<Void> handlePromptResponse(ChatMessageResponseEvent event);
}
