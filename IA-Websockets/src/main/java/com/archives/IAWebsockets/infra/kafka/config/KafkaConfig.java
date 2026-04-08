package com.archives.IAWebsockets.infra.kafka.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import com.archives.IAWebsockets.domain.messaging.ChatMessageRequestEvent;
import com.archives.IAWebsockets.domain.messaging.ChatMessageResponseEvent;

import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS_CONFIG;

    // Productor Reactivo (Configuracion)
    @Bean
    // clase principal de reactor kafka para enviar log distribuidos de forma
    // reactiva
    public KafkaSender<String, ChatMessageRequestEvent> kafkaSender() {

        // configuracion del productor de kafka
        Map<String, Object> configKafkaParamsProducer = Map.of(
                // para que encuentre el cluster de kafka cuando se levante el servidor

                /**
                 * 1. El productor se conecta al broker que se le ha indicado
                 * 2. Ese broker retorna toda la lista de brokers
                 * 3. despues ya sabe a donde enviar cada mensaje
                 */
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG,

                // kafka solo trasmite bytes y lo hace mediante serializers es transformar la
                // key a bytes
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,

                // como el value es un objeto necesitamos serializarlo a bytes para kafka
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);

        // configuracion real del productor de kafka con toda su configuracion global
        SenderOptions<String, ChatMessageRequestEvent> options = SenderOptions.create(configKafkaParamsProducer);

        return KafkaSender.create(options); // creamos el sender
    }

    // Customer Reactivo (configuracion)
    @Bean
    public KafkaReceiver<String, ChatMessageResponseEvent> kafkaReceiver() {

        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "chat-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        JacksonJsonDeserializer<ChatMessageResponseEvent> deserializer = new JacksonJsonDeserializer<>(
                ChatMessageResponseEvent.class);

        deserializer.addTrustedPackages("com.archives.IAWebsockets.domain.messaging");

        ReceiverOptions<String, ChatMessageResponseEvent> options = ReceiverOptions
                .<String, ChatMessageResponseEvent>create(props)
                .withValueDeserializer(deserializer)
                .subscription(List.of("chat-response"));

        return KafkaReceiver.create(options);
    }

}
