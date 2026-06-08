package com.files.LiveProductLive.infra.kafka.records;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.kafka")
public record KafkaProperties(String bootstrapServer, String productViewTopic, String consumerGroup) {

}
