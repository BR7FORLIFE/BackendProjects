package com.archives.IAWebsockets.infra.websockets.config;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.archives.IAWebsockets.domain.messaging.ChatMessageRequestEvent;
import com.fasterxml.jackson.databind.JsonSerializer;

import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class KafkaConfig {

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
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",

                // kafka solo trasmite bytes y lo hace mediante serializers es transformar la
                // key a bytes
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,

                // como el value es un objeto necesitamos serializarlo a bytes para kafka
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // configuracion real del productor de kafka con toda su configuracion global
        SenderOptions<String, ChatMessageRequestEvent> options = SenderOptions.create(configKafkaParamsProducer);

        return KafkaSender.create(options); // creamos el sender
    }

    // Customer Reactivo (configuracion)
    @Bean
    // es la clase principal de reactor kafka para consumir mensajes
    public KafkaReceiver<String, ChatMessageRequestEvent> kafkaReceiver() {

        // definimos la configuracion del consumer de kafka
        Map<String, Object> configKafkaParamsCustomer = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",

                // definimos el consumer group
                /**
                 * un consumer group nos permite procesar mensajes en paralelo
                 * y distribuir la carga
                 * 
                 */
                ConsumerConfig.GROUP_ID_CONFIG, "chat-group",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // configuracion de kafka, topics a consumir, etc etc
        ReceiverOptions<String, ChatMessageRequestEvent> options = ReceiverOptions
                .<String, ChatMessageRequestEvent>create(configKafkaParamsCustomer)
                .subscription(List.of("chat-requests")); // le dice a que topics debe subscribirse el customer

        return KafkaReceiver.create(options);
    }
}
