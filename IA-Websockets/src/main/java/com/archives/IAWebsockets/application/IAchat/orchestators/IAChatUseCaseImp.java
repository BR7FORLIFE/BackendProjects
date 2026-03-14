package com.archives.IAWebsockets.application.IAchat.orchestators;

import org.springframework.stereotype.Service;

import com.archives.IAWebsockets.application.IAchat.ports.IAChatPort;
import com.archives.IAWebsockets.application.IAchat.usecases.IAChatUseCase;
import com.archives.IAWebsockets.application.messaging.ports.MessageCustomerPort;
import com.archives.IAWebsockets.domain.messaging.ChatMessageResponseEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IAChatUseCaseImp implements IAChatUseCase {

    private final IAChatPort chatPort;

    private final MessageCustomerPort customerPort;

    public IAChatUseCaseImp(IAChatPort port, MessageCustomerPort customerPort) {
        this.chatPort = port;
        this.customerPort = customerPort;
    }

    @Override
    public Mono<Void> processMessage(ChatMessageResponseEvent event) {
        return chatPort.handlePromptResponse(event);
    }

    public Flux<Void> startedConsumed() {
        return customerPort.receiveChatResponse()
                .flatMap(chatPort::handlePromptResponse);
    }
}
