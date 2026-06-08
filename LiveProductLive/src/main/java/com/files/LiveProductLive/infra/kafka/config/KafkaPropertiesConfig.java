package com.files.LiveProductLive.infra.kafka.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.files.LiveProductLive.infra.kafka.records.KafkaProperties;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaPropertiesConfig {

}
