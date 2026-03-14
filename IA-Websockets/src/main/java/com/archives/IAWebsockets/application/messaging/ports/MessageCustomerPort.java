package com.archives.IAWebsockets.application.messaging.ports;

import com.archives.IAWebsockets.domain.messaging.ChatMessageResponseEvent;

import reactor.core.publisher.Flux;

public interface MessageCustomerPort {
    Flux<ChatMessageResponseEvent> receiveChatResponse();
}
