package com.archives.IAWebsockets.infra.IA;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.archives.IAWebsockets.application.IAchat.ports.IAChatPort;
import com.archives.IAWebsockets.application.messaging.ports.MessagePublisherPort;
import com.archives.IAWebsockets.domain.messaging.ChatMessageRequestEvent;
import com.archives.IAWebsockets.domain.messaging.ChatMessageResponseEvent;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class OllamaAdapter implements IAChatPort {

    private final ChatClient chatClient;
    // puerto para comunicarse con el servicio de kafka como consumidor (consumer)
    private final MessagePublisherPort publisherPort;

    public OllamaAdapter(ChatClient chatClient, MessagePublisherPort publisherPort) {
        this.chatClient = chatClient;
        this.publisherPort = publisherPort;
    }

    @Override
    public Mono<Void> handlePromptResponse(ChatMessageResponseEvent event) {
        return callModel(event.getResponse())
                .flatMap(responseAI -> {
                    ChatMessageRequestEvent eventIA = new ChatMessageRequestEvent(event.getSessionId(), responseAI);

                    return publisherPort.publishChatRequest(eventIA);
                });
    }

    public Mono<String> callModel(String message) {
        return Mono.fromCallable(() -> {
            String modelMessage = chatClient.prompt(message).call().content();
            return modelMessage;
        }).subscribeOn(Schedulers.boundedElastic());

    }
}
