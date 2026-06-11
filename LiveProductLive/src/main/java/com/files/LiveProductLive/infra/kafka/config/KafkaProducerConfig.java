package com.files.LiveProductLive.infra.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.files.LiveProductLive.infra.kafka.records.KafkaProperties;

import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class KafkaProducerConfig {

    private final KafkaProperties properties;

    public KafkaProducerConfig(KafkaProperties kafkaProperties) {
        this.properties = kafkaProperties;
    }

    // serializar es de objeto -> byte
    // deserializar es de byte -> objeto
    @Bean
    public SenderOptions<String, String> senderOptions() {

        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.bootstrapServer());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return SenderOptions.create(config);
    }

    @Bean
    public KafkaSender<String, String> kafkaSender(SenderOptions<String, String> options) {
        return KafkaSender.create(options);
    }

}
