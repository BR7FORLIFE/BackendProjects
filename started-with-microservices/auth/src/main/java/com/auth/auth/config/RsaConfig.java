package com.auth.auth.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RsaConfig {

    @Bean
    public KeyPair keyPairGenerator() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }
}
