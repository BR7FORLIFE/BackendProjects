package com.archives.IAWebsockets.application.messaging.orchestators;

import org.springframework.stereotype.Service;

import com.archives.IAWebsockets.application.messaging.ports.MessageCustomerPort;
import com.archives.IAWebsockets.application.messaging.ports.MessagePublisherPort;
import com.archives.IAWebsockets.application.messaging.usecases.MessagingUseCase;
import com.archives.IAWebsockets.domain.messaging.ChatMessageRequestEvent;
import com.archives.IAWebsockets.domain.messaging.ChatMessageResponseEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessagingUseCaseImp implements MessagingUseCase {

    // puerto para comunicarse con el servicio de kafka como productor (producer)
    private final MessagePublisherPort messagePublisher;

    // puerto para comunicarse con el servicio de kafka como consumidor (consumer)
    private final MessageCustomerPort messageCustomer;

    public MessagingUseCaseImp(MessagePublisherPort messagePublisher, MessageCustomerPort messageCustomer) {
        this.messagePublisher = messagePublisher;
        this.messageCustomer = messageCustomer;
    }

    @Override
    public Mono<Void> handleMessage(String sessionId, String message) {
        ChatMessageRequestEvent messageEvent = new ChatMessageRequestEvent(sessionId, message);
        return messagePublisher.publishChatRequest(messageEvent);
    }

    @Override
    public Flux<ChatMessageResponseEvent> handleResponse(String sessionId) {
        return messageCustomer.receiveChatResponse()
                .filter(event -> event.getSessionId().equals(sessionId));
    }
}
