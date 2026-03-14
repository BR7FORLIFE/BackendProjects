package com.archives.IAWebsockets.infra.kafka;

import org.springframework.stereotype.Service;

import com.archives.IAWebsockets.application.messaging.ports.MessageCustomerPort;
import com.archives.IAWebsockets.domain.messaging.ChatMessageResponseEvent;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;

@Service
public class KafkaMessageCustomer implements MessageCustomerPort {

    private final KafkaReceiver<String, ChatMessageResponseEvent> receiver;

    public KafkaMessageCustomer(KafkaReceiver<String, ChatMessageResponseEvent> receiver) {
        this.receiver = receiver;
    }

    @Override
    public Flux<ChatMessageResponseEvent> receiveChatResponse() {
        return receiver.receive()
                .map(record -> record.value());
    }
}
