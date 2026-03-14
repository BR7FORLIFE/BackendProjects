package com.archives.IAWebsockets.infra.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import com.archives.IAWebsockets.application.messaging.ports.MessagePublisherPort;
import com.archives.IAWebsockets.domain.messaging.ChatMessageRequestEvent;

import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

//productor de kafka
@Component
public class KafkaMessagePublisher implements MessagePublisherPort {

    private final KafkaSender<String, ChatMessageRequestEvent> sender; // llamamos al sender de kafka

    public KafkaMessagePublisher(KafkaSender<String, ChatMessageRequestEvent> sender) {
        this.sender = sender;
    }

    @Override
    public Mono<Void> publishChatRequest(ChatMessageRequestEvent event) {
        /**
         * SenderRecord -> record para enviar el evento a kafka
         */
        SenderRecord<String, ChatMessageRequestEvent, String> record = SenderRecord.create(
                new ProducerRecord<>(
                        "chat-requests",
                        event.getSessionId(),
                        event),
                event.getSessionId());

        return sender.send(Mono.just(record)).then();
    }
}
