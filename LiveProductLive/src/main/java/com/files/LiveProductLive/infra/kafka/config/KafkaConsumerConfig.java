package com.files.LiveProductLive.infra.kafka.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.files.LiveProductLive.infra.kafka.records.KafkaProperties;

import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

@Configuration
public class KafkaConsumerConfig {

    private final KafkaProperties properties;

    public KafkaConsumerConfig(KafkaProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ReceiverOptions<String, String> receiverOptions() {

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.bootstrapServer());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, properties.consumerGroup()); // consumer group
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // sino tengo offset guardados (mensajes) empieza desde el principio como el
        // from-beginning
        // en caso tal que si haya offset el parametro earliest se ignorará y se
        // readudara desde el ultimo offset
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ReceiverOptions<String, String> options = ReceiverOptions.create(config);

        // nos subcribimos al topic como consumidores
        return options.subscription(List.of(properties.productViewTopic()));
    }

    @Bean
    public KafkaReceiver<String, String> kafkaReceiver(ReceiverOptions<String, String> options) {
        return KafkaReceiver.create(options);
    }
}
