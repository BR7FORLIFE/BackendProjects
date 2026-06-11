package com.files.LiveProductLive.application.viewProducts.publishers;

import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import com.files.LiveProductLive.infra.kafka.records.KafkaProperties;

import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
public class ProductViewProducer {

    private final KafkaSender<String, String> sender;
    private final KafkaProperties kafkaProperties;

    public ProductViewProducer(KafkaSender<String, String> sender, KafkaProperties properties) {
        this.sender = sender;
        this.kafkaProperties = properties;
    }

    public Mono<Void> publish(UUID productId) {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                this.kafkaProperties.productViewTopic(), productId.toString(), productId.toString());

        return sender.send(
                Mono.just(SenderRecord.create(record, null))).then();
    }
}
