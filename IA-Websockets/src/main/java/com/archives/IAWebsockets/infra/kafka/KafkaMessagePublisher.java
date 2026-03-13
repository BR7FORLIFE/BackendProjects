package com.archives.IAWebsockets.infra.kafka;

import org.springframework.stereotype.Component;

import com.archives.IAWebsockets.application.messaging.ports.MessagePublisherPort;
import com.archives.IAWebsockets.domain.messaging.ChatMessageRequestEvent;

import reactor.core.publisher.Mono;

@Component
public class KafkaMessagePublisher implements MessagePublisherPort {

    @Override
    public Mono<Void> publishChatRequest(ChatMessageRequestEvent event) {
        return null;
    }
}
