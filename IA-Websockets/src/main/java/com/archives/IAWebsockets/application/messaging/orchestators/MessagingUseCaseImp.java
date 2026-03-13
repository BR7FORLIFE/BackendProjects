package com.archives.IAWebsockets.application.messaging.orchestators;

import org.springframework.stereotype.Service;

import com.archives.IAWebsockets.application.messaging.ports.MessagePublisherPort;
import com.archives.IAWebsockets.application.messaging.usecases.MessagingUseCase;
import com.archives.IAWebsockets.domain.messaging.ChatMessageRequestEvent;

import reactor.core.publisher.Mono;

@Service
public class MessagingUseCaseImp implements MessagingUseCase {

    // puerto para comunicarse con el servicio de kafka
    private final MessagePublisherPort messagePublisher;

    public MessagingUseCaseImp(MessagePublisherPort messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @Override
    public Mono<Void> handleMessage(String sessionId, String message) {
        ChatMessageRequestEvent messageEvent = new ChatMessageRequestEvent(sessionId, message);
        return messagePublisher.publishChatRequest(messageEvent);
    }
}
